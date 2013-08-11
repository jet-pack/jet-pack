package br.com.jetpack.packages.system;

public class PkgAndVersion {

	private String minimalVersion;
	private String name;

	public PkgAndVersion(String name, String minimalVersion) {
		super();
		this.minimalVersion = minimalVersion;
		this.name = name;
	}

	public String getMinimalVersion() {
		return minimalVersion;
	}

	public String getName() {
		return name;
	}

}
