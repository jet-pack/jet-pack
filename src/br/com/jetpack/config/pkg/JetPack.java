package br.com.jetpack.config.pkg;

import java.io.File;
import java.io.IOException;

import br.com.jetpack.Utils.ZipUtils;

public class JetPack {

	public void makeJatPack(PkgBuild pkg) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("java.io.tmpdir"));
		sb.append("\\");
		sb.append(pkg.getName());
		sb.append(".jetpack");
		File[] files = new File[pkg.getSource().length + 1];
		for (int i = 0; i < pkg.getSource().length; i++) {
			files[i] = new File(pkg.getSource()[i]);
		}
		files[pkg.getSource().length] = new File("pkgBuild.properties"); 
		ZipUtils.compress(files, new File(sb.toString()));
	}
	
}
