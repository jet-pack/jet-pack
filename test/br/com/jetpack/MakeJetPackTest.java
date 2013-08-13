package br.com.jetpack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import br.com.jetpack.Utils.ZipUtils;
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
		pkg.makeJetPack();
		String tempPath = System.getProperty("java.io.tmpdir") + "\\";
		String fileName = tempPath + pkg.getName() + ".jetpack";
		File generatedFile = new File(fileName);
		assertTrue("Não criou o arquivo no destino.", generatedFile.exists());

		File generatedDir = new File(tempPath + pkg.getName());
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
		final byte[] bufferExpected = new byte[bufferSize];
		final byte[] bufferGenerated = new byte[bufferSize];
		while (fisExpected.read(bufferExpected, 0, bufferSize) != -1) {
			fisGenerated.read(bufferGenerated, 0, bufferSize);
			assertEquals("Falha na comparação do arquivo: " + fileExpected.getName(), bufferExpected, bufferGenerated);
		}
		fisGenerated.close();
		fisExpected.close();
	}
}
