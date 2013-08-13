package br.com.jetpack;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import br.com.jetpack.Utils.ZipUtils;
import br.com.jetpack.config.pkg.JetPack;
import br.com.jetpack.config.pkg.PkgBuild;
import br.com.jetpack.config.pkg.reader.PkgInfoJsonReader;
import br.com.jetpack.config.pkg.reader.PropertyNotFoundException;

public class MakeJetPackTest {

	@Test
	public void testMakeJetPack() throws IOException, PropertyNotFoundException {
		PkgInfoJsonReader reader = new PkgInfoJsonReader();
		reader.load("test\\resources\\Sourcespkgbuild.js");
		PkgBuild pkg = new PkgBuild();
		pkg.load(reader);

		JetPack jp = new JetPack();
		jp.makeJatPack(pkg);

		assertCorrectGeneration(pkg);
	}

	private void assertCorrectGeneration(PkgBuild pkg) throws IOException {
		String tempPath = System.getProperty("java.io.tmpdir") + "\\";
		String fileName = tempPath + pkg.getName() + ".jetpack";
		File generatedFile = new File(fileName);
		assertTrue("Não criou o arquivo no destino.", generatedFile.exists());

		File generatedDir = new File(tempPath + pkg.getName() + "\\Sources");
		ZipUtils.extract(generatedFile, generatedDir);

		File expectedDir = new File("test\\resources\\Sources");
		assertTrue("Após descompactação, não foi gerada uma pasta", generatedDir.isDirectory());
		assertDirEquals(expectedDir, generatedDir);
	}

	private void assertDirEquals(File expectedDir, File generatedDir)
			throws IOException {
		File[] listFiles = expectedDir.listFiles();
		for (File fileExpected : listFiles) {
			File fileGenerated = new File(generatedDir.getAbsolutePath() + "\\" + fileExpected.getName());
			if (fileExpected.isDirectory()) {
				assertDirEquals(fileExpected, fileGenerated);
			} else {
				assertFile(fileExpected, fileGenerated);
			}
		}
	}

	private void assertFile(File fileExpected, File fileGenerated)
			throws FileNotFoundException, IOException {
		FileInputStream fisExpected = new FileInputStream(fileExpected);
		FileInputStream fisGenerated = new FileInputStream(fileGenerated);
		final int bufferSize = 4096;
		final byte[] buffer = new byte[bufferSize];
		ByteArrayOutputStream expected = new ByteArrayOutputStream();
		int lidos = -1;
		while ((lidos = fisExpected.read(buffer, 0, bufferSize)) != -1) {
			expected.write(buffer, 0, lidos);
		}
		ByteArrayOutputStream generated = new ByteArrayOutputStream();
		while ((lidos = fisGenerated.read(buffer, 0, bufferSize)) != -1) {
			generated.write(buffer, 0, lidos);
		}

		assertTrue("Falha na comparação do arquivo: " + fileExpected.getName(), Arrays.equals(expected.toByteArray(), generated.toByteArray()));

		fisGenerated.close();
		fisExpected.close();
	}
}
