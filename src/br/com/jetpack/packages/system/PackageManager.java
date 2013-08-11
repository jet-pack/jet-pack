package br.com.jetpack.packages.system;

import java.util.ArrayList;
import java.util.List;

import br.com.jetpack.config.pkg.PkgInfo;
import br.com.jetpack.config.pkg.loader.PackageLoader;

public class PackageManager {

	PackageLoader local, remote;
	LogicalPackageManager manager = new LogicalPackageManager();

	public PackageManager(PackageLoader local, PackageLoader remote) {
		super();
		this.local = local;
		this.remote = remote;
	}

	/**
	 * Installs the package and their dependencies
	 * 
	 * @param pkgInfo
	 * @return Return false if already installed.
	 * @throws LessVersionException
	 */
	public boolean install(PkgInfo pkgInfo) throws LessVersionException {
		if (isInstalled(pkgInfo.getName())) {
			return false;
		}
		List<String> pkgsToInstall = new ArrayList<String>();
		checkDependencies(pkgInfo.getDependsWithVersion(), pkgsToInstall);

		for (String pkgName : pkgsToInstall) {
			remote.loadInstallerScript(pkgName).install();
		}
		return true;
	}

	public boolean isInstalled(String pkgName) {
		return manager.isInstalled(pkgName);
	}

	/**
	 * Check dependencies between packages and mounts the list of packages that
	 * will be installed
	 * 
	 * @param dependsWithVersion
	 * @param pkgsToInstall
	 * @throws LessVersionException
	 */
	private void checkDependencies(final PkgAndVersion[] dependsWithVersion, List<String> pkgsToInstall) throws LessVersionException {
		for (PkgAndVersion dep : dependsWithVersion) {
			final String pkgName = dep.getName();

			if (!manager.isInstalled(pkgName)) {
				// first check and add depends in pkgsToInstall
				checkDependencies(remote.loadPackage(pkgName).getDependsWithVersion(), pkgsToInstall);
				// after add self
				pkgsToInstall.add(pkgName);
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
