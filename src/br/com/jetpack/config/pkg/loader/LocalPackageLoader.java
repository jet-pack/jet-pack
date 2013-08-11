package br.com.jetpack.config.pkg.loader;

import br.com.jetpack.config.pkg.PkgBuild;
import br.com.jetpack.config.pkg.PkgInfo;
import br.com.jetpack.packages.installer.Installer;

public class LocalPackageLoader implements PackageLoader {

	@Override
	public PkgInfo loadPackage(String pkgName) {
		PkgBuild build = new PkgBuild();
		return build;
	}

	@Override
	public Installer loadInstallerScript(String pkgName) {
		// TODO Auto-generated method stub
		return null;
	}

}
