package br.com.jetpack.packages.system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LogicalPackageManagerTest {

	LogicalPackageManager manager = new LogicalPackageManager();
	final String JAVA = "java";
	final String ECLIPSE = "eclipse";

	@Before
	public void before() {
		manager.add(new TestInstalledPackage(JAVA, new String[] {}));
		manager.add(new TestInstalledPackage(ECLIPSE, new String[] { JAVA }));
	}

	@Test
	public void testRemove() {
		assertFalse("Não deveria ter removido", manager.remove(JAVA));
		assertTrue("Deveria ter removido", manager.remove(ECLIPSE));
		assertTrue("Deveria ter removido", manager.remove(JAVA));
	}

	@Test
	public void testHasDependents() {
		assertTrue("Deveria ter dependentes", manager.hasDependents(JAVA));
		assertFalse("Não deveria ter dependentes", manager.hasDependents(ECLIPSE));
	}

	@Test
	public void testIsInstalled() {
		assertTrue("Deveria estar instalado", manager.isInstalled(JAVA));
		assertTrue("Deveria estar instalado", manager.isInstalled(ECLIPSE));
		assertFalse("Não deveria estar instalado", manager.isInstalled("blalbla"));
		assertTrue("Deveria ter removido", manager.remove(ECLIPSE));
		assertFalse("Não deveria estar instalado", manager.isInstalled(ECLIPSE));
	}

}
