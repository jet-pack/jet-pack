package br.com.jetpack.packages.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import br.com.jetpack.config.pkg.InstalledPkgInfo;

/**
 * Logical information of installed package. This class don't go remove/add
 * phisicaling package.
 * 
 * @author renatol
 * 
 */
public class LogicalPackageManager {

	private Map<String, LogicalPackage> packages = new HashMap<String, LogicalPackage>();

	/**
	 * Add package in installed packages.
	 * 
	 * @param pkg
	 */
	public void add(InstalledPkgInfo pkg) {
		updateDependents(pkg);
		packages.put(pkg.getName(), new LogicalPackage(pkg.getName(), pkg.getDepends()));
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
		boolean hasDependents = hasDependents(pkgName);
		if (!hasDependents) {
			packages.remove(pkgName);
		}
		return hasDependents;
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
		return packages.get(pkgName).getDependents().length > 0;
	}

	/**
	 * Load logical package from disk
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */

	@SuppressWarnings("unchecked")
	public boolean load(String fileName) throws FileNotFoundException, IOException {
		boolean isEmpty = packages.isEmpty();
		if (isEmpty) {
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(fileName)));
				try {
					packages = (Map<String, LogicalPackage>) objectInputStream.readObject();
				} finally {
					objectInputStream.close();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return isEmpty;
	}

	/**
	 * Save logical package from disk
	 * 
	 * @param fileName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void save(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
		try {
			objectOutputStream.writeObject(packages);
		} finally {
			objectOutputStream.close();
		}
	}
}
