package br.com.jetpack.config.pkg.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.jetpack.packages.system.PkgAndVersion;

public interface PkgInfoReader {

	String getStr(String key) throws PropertyNotFoundException;

	String[] getStrArray(String key) throws PropertyNotFoundException;

	PkgAndVersion[] getPkgAndVersions(String key) throws PropertyNotFoundException;

	PkgAndVersion[] optPkgAndVersions(String key);

	String optStr(String key);

	String[] optStrArray(String key);

	boolean contains(String key);

	void load(String fileName) throws FileNotFoundException, IOException;
}
