package br.com.jetpack.packages.system;

import br.com.jetpack.config.pkg.InstalledPkgInfo;

public class TestInstalledPackage extends InstalledPkgInfo {

	public TestInstalledPackage(String name, String[] depends) {
		this.name = name;
		this.depends = depends;
	}
}
