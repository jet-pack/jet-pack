package br.com.jetpack.config.pkg.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PkgInfoReader {

	String getStr(String key);

	String[] getStrArray(String key);

	void load(String fileName) throws FileNotFoundException, IOException;
}
