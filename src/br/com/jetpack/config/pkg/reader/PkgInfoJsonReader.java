package br.com.jetpack.config.pkg.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.jetpack.config.json.JSONArray;
import br.com.jetpack.config.json.JSONObject;
import br.com.jetpack.packages.system.PkgAndVersion;

public class PkgInfoJsonReader implements PkgInfoReader {

	private JSONObject object = null;

	@Override
	public String getStr(String key) throws PropertyNotFoundException {
		checkObject();
		try {
			return object.getString(key);
		} catch (Exception e) {
			throw new PropertyNotFoundException(key);
		}

	}

	@Override
	public String[] getStrArray(String key) throws PropertyNotFoundException {
		checkObject();
		JSONArray array = null;
		try {
			array = object.getJSONArray(key);
		} catch (Exception e) {
			throw new PropertyNotFoundException(key);
		}
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
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} finally {
			reader.close();
		}

		sb.append("}");
		object = new JSONObject(sb.toString());
	}

	@Override
	public boolean contains(String key) {
		return object.has(key);
	}

	@Override
	public String optStr(String key) {
		return object.optString(key);
	}

	@Override
	public String[] optStrArray(String key) {
		if (!object.has(key)) {
			return new String[] {};
		}
		try {
			return getStrArray(key);
		} catch (PropertyNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PkgAndVersion[] getPkgAndVersions(String key) throws PropertyNotFoundException {
		String[] array = getStrArray(key);
		List<PkgAndVersion> list = new ArrayList<PkgAndVersion>();

		for (String value : array) {
			String[] versionName = value.split(">=");
			if (versionName.length > 1) {
				list.add(new PkgAndVersion(versionName[0], versionName[1]));
			} else {
				list.add(new PkgAndVersion(versionName[0], ""));
			}
		}
		return list.toArray(new PkgAndVersion[] {});
	}

	@Override
	public PkgAndVersion[] optPkgAndVersions(String key) {
		if (!object.has(key)) {
			return new PkgAndVersion[] {};
		}
		try {
			return getPkgAndVersions(key);
		} catch (PropertyNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
