package br.com.jetpack.packages.installer;

import br.com.jetpack.config.pkg.PkgInfo;

/**
 * Class that do installation of fact
 * 
 * @author renatol
 * 
 */

public class InstallerManager {

	private InstallerScript installer;
	private PkgInfo pkgInfo;

	public InstallerManager(PkgInfo pkgInfo, InstallerScript installer) {
		super();
		this.pkgInfo = pkgInfo;
		this.installer = installer;
	}

	public void install() {

	}

}
