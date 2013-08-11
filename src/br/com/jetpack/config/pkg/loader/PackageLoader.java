package br.com.jetpack.config.pkg.loader;

import br.com.jetpack.config.pkg.PkgInfo;
import br.com.jetpack.packages.installer.Installer;

public interface PackageLoader {
	PkgInfo loadPackage(String pkgName);

	Installer loadInstallerScript(String pkgName);

}