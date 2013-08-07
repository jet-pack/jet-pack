package br.com.jaman.config.pkg;

import java.io.File;

public class PackageFactory {

	public static PkgInfo loadPackage(File file) {
		PkgBuild build = new PkgBuild();
		return build;
	}
}
