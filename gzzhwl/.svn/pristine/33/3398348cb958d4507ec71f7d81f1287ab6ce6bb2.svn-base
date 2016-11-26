package org.roundface.model;

import org.roundface.util.ProReader;

public class PackageConfig {
	private String model;
	private String dao;
	private String daoImpl;
	private String base;
	private String extdao;
	private String extDaoImpl;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(String daoImpl) {
		this.daoImpl = daoImpl;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getExtdao() {
		return extdao;
	}

	public void setExtdao(String extdao) {
		this.extdao = extdao;
	}

	public String getExtDaoImpl() {
		return extDaoImpl;
	}

	public void setExtDaoImpl(String extDaoImpl) {
		this.extDaoImpl = extDaoImpl;
	}

	public static PackageConfig getConfig() throws Exception {
		PackageConfig config = new PackageConfig();
		config.setDao(ProReader.getInstance().getProperty("dao.package"));
		config.setDaoImpl(ProReader.getInstance().getProperty("dao.impl.package"));
		config.setModel(ProReader.getInstance().getProperty("model.package"));
		config.setBase(ProReader.getInstance().getProperty("base.package"));
		config.setExtdao(ProReader.getInstance().getProperty("extdao.package"));
		config.setExtDaoImpl(ProReader.getInstance().getProperty("extdao.impl.package"));
		return config;
	}

}
