package org.roundface.model;

import java.util.ArrayList;
import java.util.List;

import org.roundface.util.ProReader;
import org.roundface.util.StringUtility;

public class Tabel {

	private String tabelName;
	private String cnName;
	private String cat;
	private String schem;
	private List<Column> pkCloums = new ArrayList<Column>();
	private List<Column> fkCloums = new ArrayList<Column>();
	private List<Column> cloums = new ArrayList<Column>();

	public String getTabelName() {
		return tabelName;
	}

	public void setTabelName(String tabelName) {
		this.tabelName = tabelName;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getSchem() {
		return schem;
	}

	public void setSchem(String schem) {
		this.schem = schem;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public void addCloum(Column cloum) {
		cloums.add(cloum);
	}

	public List<Column> getPkCloums() {
		return pkCloums;
	}

	public void setPkCloums(List<Column> pkCloums) {
		this.pkCloums = pkCloums;
	}

	public List<Column> getCloums() {
		return cloums;
	}

	public void setCloums(List<Column> cloums) {
		this.cloums = cloums;
	}

	public List<Column> getFkCloums() {
		return fkCloums;
	}

	public void setFkCloums(List<Column> fkCloums) {
		this.fkCloums = fkCloums;
	}

	public String getClassName() {
		String tableFix = ProReader.getInstance().getProperty("table.fix");
		String s = tabelName.toUpperCase();
		if (s.indexOf(tableFix) == 0) {
			s = s.substring(tableFix.length());
		}
		return StringUtility.getCamelCaseString(s, true);
	}

	public String getVarName() {
		String tableFix = ProReader.getInstance().getProperty("table.fix");
		String s = tabelName.toUpperCase();
		if (s.indexOf(tableFix) == 0) {
			s = s.substring(tableFix.length());
		}
		return StringUtility.getCamelCaseString(s, false);
	}

	public String getFixTableName() {
		String tableFix = ProReader.getInstance().getProperty("table.fix").toUpperCase();
		String s = tabelName.toUpperCase();
		if (s.indexOf(tableFix) == 0) {
			s = s.substring(tableFix.length());
		}
		return s;
	}

}
