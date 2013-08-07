package br.com.jetpack.config.pkg.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class PkgInfoJsonReader implements PkgInfoReader {

	private JSONObject object = null;

	@Override
	public String getStr(String key) {
		checkObject();
		return object.getString(key);
	}

	@Override
	public String[] getStrArray(String key) {
		checkObject();
		JSONArray array = object.getJSONArray(key);
		int length = array.length();
		String[] ret = new String[length];
		for (int i = 0; i < length; i++) {
			ret[i] = array.getString(i);
		}
		return ret;
	}

	private void checkObject() {
		assert object != null : "Deve-se primeiro dar um load na classe antes de acessar o valor";
	}

	@Override
	public void load(String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");

		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
		try {
			String line;
			while ((line = reader.readLine()) != "") {
				sb.append(line + "\n");
			}
		} finally {
			reader.close();
		}

		sb.append("}");
		object = new JSONObject(sb.toString());
	}
}
