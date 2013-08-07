package br.com.jaman.packages.system;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.jaman.config.EnvConfig;
import br.com.jaman.config.pkg.InstalledPkgInfo;
import br.com.jaman.config.pkg.PackageFactory;
import br.com.jaman.config.pkg.PkgInfo;

/**
 * Logical information of installed package. This class don't go remove/add
 * phisicaling package.
 * 
 * @author renatol
 * 
 */
public class LogicalPackageManager {

	private EnvConfig config;
	// Lazy load pkgInfo
	private Map<String, LogicalPackage> packages = new HashMap<String, LogicalPackage>();

	public LogicalPackageManager(EnvConfig config) {
		super();
		this.config = config;
	}

	/**
	 * Add package in installed packages.
	 * 
	 * @param pkg
	 */
	public void add(InstalledPkgInfo pkg) {
		updateDependents(pkg);
		packages.put(pkg.getName(),
				new LogicalPackage(pkg.getName(), pkg.getDepends()));
	}

	/**
	 * Add new pacote with dependent of depends packages.
	 * 
	 * @param pkg
	 */
	private void updateDependents(InstalledPkgInfo pkg) {
		String name = pkg.getName();
		for (String dependName : pkg.getDepends()) {
			LogicalPackage depend = packages.get(dependName);
			depend.addDependents(name);
		}
	}

	/**
	 * Remove package in installed packages.
	 * 
	 * @param pkgName
	 */
	public boolean remove(String pkgName) {

		if (!hasDependents(pkgName)) {
			packages.remove(pkgName);
		} else {
			LogicalPackage pkg = packages.get(pkgName);
			for (String dependent : pkg.getDepends()) {
				// If any depends has dependents, can't removed
				if (packages.get(dependent).getDependents().length > 0) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Return package definition
	 * 
	 * @param pkgName
	 * @return
	 */
	public PkgInfo getPackageInfo(String pkgName) {
		File filePkg = new File(config.getInternalsPath() + "packages/"
				+ pkgName + "/pkgbuild.properties");
		return PackageFactory.loadPackage(filePkg);
	}

	/**
	 * Check if package is installed
	 * 
	 * @param pkgName
	 * @return
	 */
	public boolean isInstalled(String pkgName) {
		return packages.containsKey(pkgName);
	}

	/**
	 * Return if package is dependency of any package.
	 * 
	 * @param pkgName
	 * @return
	 */
	public boolean hasDependents(String pkgName) {
		return false;// dependencies.contains(pkgName);
	}

	/**
	 * Load logical package from disk
	 * 
	 */
	public void load() {

	}

	/**
	 * Save logical package from disk
	 * 
	 */
	public void save() {

	}
}
