package br.com.jetpack.packages.installer;

public interface InstallerScript {

	void preInstall(String version);

	void postInstall(String version);

	void preUpgrade(String newVersion, String oldVersion);

	void posUpgrade(String newVersion, String oldVersion);

	void preRemove(String version);

	void postRemove(String version);

}
