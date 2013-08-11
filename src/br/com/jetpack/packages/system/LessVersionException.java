package br.com.jetpack.packages.system;

/**
 * Exception returned when the package version installed is less than the
 * required for new package being installed.
 * 
 * @author renatol
 * 
 */
public class LessVersionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LessVersionException(String pkgName, String version, String minimalVersion) {
		super(String.format("The version (%s) of package %s is less of the required minimal version %s", version, pkgName, minimalVersion));
	}

}
