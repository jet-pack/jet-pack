package br.com.jetpack.config.pkg.reader;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.jetpack.config.pkg.PkgBuild;
import br.com.jetpack.packages.system.PkgAndVersion;

public class PkgInfoJsonReaderTest {

	String[] source = { "\\\\maquina\\packages\\mypackageFiles",//
			"\\\\maquina\\packages\\$name-$version.zip", //
			"C:/temp/file.txt" };
	PkgInfoJsonReader reader = new PkgInfoJsonReader();

	@Before
	public void before() throws IOException {
		reader = new PkgInfoJsonReader();
		reader.load("test/resources/pkgbuild.js");
	}

	@Test
	public void testBasic() throws IOException, PropertyNotFoundException {
		assertEquals("name", reader.getStr("name"));
		assertArrayEquals(source, reader.getStrArray("source"));
	}

	@Test
	public void testPkgInfo() throws IOException, PropertyNotFoundException {
		PkgBuild pkg = new PkgBuild();
		pkg.load(reader);
		assertEquals("name", pkg.getName());
		assertArrayEquals(source, pkg.getSource());
	}

	@Test
	public void testGetPkgVersion() throws PropertyNotFoundException {
		final String NAME_ERROR = "O nome foi lido errado.";
		final String VERSION_ERROR = "A versão foi lida errada.";

		PkgAndVersion[] pkgs = reader.getPkgAndVersions("depends");
		assertEquals("Numero de pacotes diferente do esperado", 3, pkgs.length);
		assertEquals(NAME_ERROR, "package1", pkgs[0].getName());
		assertEquals(VERSION_ERROR, "1.2.3", pkgs[0].getMinimalVersion());
		assertEquals(NAME_ERROR, "package2", pkgs[1].getName());
		assertEquals(VERSION_ERROR, "1.2.4", pkgs[1].getMinimalVersion());
		assertEquals(NAME_ERROR, "package3", pkgs[2].getName());
		assertEquals(VERSION_ERROR, "", pkgs[2].getMinimalVersion());
	}
}
