package br.com.jetpack.packages.system;

import br.com.jetpack.config.pkg.PkgInfo;
import br.com.jetpack.config.pkg.loader.PackageLoader;
import br.com.jetpack.packages.installer.Installer;

public class PackageManager {

	PackageLoader local, remote;
	LogicalPackageManager manager = new LogicalPackageManager();

	public PackageManager(PackageLoader local, PackageLoader remote) {
		super();
		this.local = local;
		this.remote = remote;
	}

	/**
	 * Instala um pacote
	 * 
	 * @param pkgInfo
	 * @throws LessVersionException
	 */
	public void install(PkgInfo pkgInfo) throws LessVersionException {
		installDependencies(pkgInfo.getDependsWithVersion());
	}

	private void installDependencies(final PkgAndVersion[] dependsWithVersion) throws LessVersionException {
		for (PkgAndVersion dep : dependsWithVersion) {
			final String pkgName = dep.getName();

			if (!manager.isInstalled(pkgName)) {
				install(remote.loadPackage(pkgName));
			} else {
				if (!"".equals(dep.getMinimalVersion())) {
					PkgInfo pkg = local.loadPackage(pkgName);

					if (!isGreaterOrEquals(pkg.getVersion(), dep.getMinimalVersion())) {
						throw new LessVersionException(pkgName, pkg.getVersion(), dep.getMinimalVersion());
					}
				}
			}
		}
	}

	/**
	 * Compare two versions and define if greater or equals <br>
	 * TODO See this real algorithm
	 * 
	 * @param version
	 * @param minimalVersion
	 * @return
	 */
	private boolean isGreaterOrEquals(String version, String minimalVersion) {
		return version.compareTo(minimalVersion) >= 0;
	}

	public void upgrade(String pkgName) {

	}

	public void remove(String pkgName) {

	}

}
