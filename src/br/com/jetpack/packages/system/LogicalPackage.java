package br.com.jetpack.packages.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LogicalPackage implements Serializable{
	private String name;
	private String[] depends;
	private List<String> dependents = new ArrayList<String>();

	public LogicalPackage(String name, String[] depends) {
		super();
		this.name = name;
		this.depends = depends;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getDepends() {
		return depends;
	}

	public void setDepends(String[] depends) {
		this.depends = depends;
	}

	public String[] getDependents() {
		return (String[]) dependents.toArray();
	}

	public void addDependents(String dependent) {
		this.dependents.add(dependent);
	}

}
