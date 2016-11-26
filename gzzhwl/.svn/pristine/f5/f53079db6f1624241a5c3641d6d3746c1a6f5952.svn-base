package org.roundface.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roundface.model.PackageConfig;
import org.roundface.model.Tabel;
import org.roundface.util.ConnetionUtils;
import org.roundface.util.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MybatisGenerator {
	private PackageConfig config = null;

	public MybatisGenerator() throws Exception {
		config = PackageConfig.getConfig();
	}

	public void gener(String[] tables) throws Exception {
		String[] types = { "TABLE" };
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(MybatisGenerator.class, "/templates");
		Connection jdbcConnection = ConnetionUtils.getConnetion();
		for (String table : tables) {
			List<Tabel> l = ConnetionUtils.getTables(jdbcConnection, types, table);
			for (Tabel t : l) {
				System.out.println(t.getTabelName());
				System.out.println("----------------------------");
				generateData(cfg, t);
			}
		}
	}
	
	public void generExt(String[] tables) throws Exception {
		String[] types = { "TABLE" };
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(MybatisGenerator.class, "/templates");
		Connection jdbcConnection = ConnetionUtils.getConnetion();
		for (String table : tables) {
			List<Tabel> l = ConnetionUtils.getTables(jdbcConnection, types, table);
			for (Tabel t : l) {
				System.out.println(t.getTabelName());
				System.out.println("----------------------------");
				generateDataExt(cfg, t);
			}
		}
	}
	
	public void generateData(Configuration cfg, Tabel t) throws IOException, TemplateException {

		Template temp = cfg.getTemplate("model_temp.ftl");
		String outdir = FileUtils.getPath() + "/data" + "/model/";
		String outfile = t.getClassName() + ".java";
		generate(t, temp, outfile, outdir);

		Template temp2 = cfg.getTemplate("dao_temp.ftl");
		String outdir2 = FileUtils.getPath() + "/data" + "/dao/";
		String outfile2 = t.getClassName() + "Dao.java";
		generate(t, temp2, outfile2, outdir2);

		Template temp1 = cfg.getTemplate("daoImpl_temp.ftl");
		String outdir1 = FileUtils.getPath() + "/data" + "/dao/impl";
		String outfile1 = t.getClassName() + "DaoImpl.java";
		generate(t, temp1, outfile1, outdir1);

		Template temp3 = cfg.getTemplate("mapper_temp.ftl");
		String outdir3 = FileUtils.getPath() + "/data" + "/mapper/";
		String outfile3 = t.getClassName() + "Mapper.xml";
		generate(t, temp3, outfile3, outdir3);
	}

	public void generateDataExt(Configuration cfg, Tabel t) throws IOException, TemplateException {

		Template temp = cfg.getTemplate("model_temp.ftl");
		String outdir = FileUtils.getPath() + "/data" + "/model/";
		String outfile = t.getClassName() + ".java";
		generate(t, temp, outfile, outdir);

		Template temp2 = cfg.getTemplate("dao_ext_temp.ftl");
		String outdir2 = FileUtils.getPath() + "/data" + "/extdao/";
		String outfile2 = t.getClassName() + "ExtDao.java";
		generate(t, temp2, outfile2, outdir2);

		Template temp1 = cfg.getTemplate("daoImpl_ext_temp.ftl");
		String outdir1 = FileUtils.getPath() + "/data" + "/extdao/impl/";
		String outfile1 = t.getClassName() + "ExtDaoImpl.java";
		generate(t, temp1, outfile1, outdir1);

		Template temp3 = cfg.getTemplate("mapper_ext_temp.ftl");
		String outdir3 = FileUtils.getPath() + "/data" + "/extmapper/";
		String outfile3 = t.getClassName() + "Mapper_ext.xml";
		generate(t, temp3, outfile3, outdir3);
	}
	

	public void generate(String domain, Tabel t, Template temp, String outfilename, String outdir)
			throws IOException, TemplateException {
		File f = new File(outdir);
		f.mkdirs();
		File fn = new File(f.getPath() + "/" + outfilename);
		fn.createNewFile();
		FileOutputStream fos = new FileOutputStream(fn);
		Writer out = new OutputStreamWriter(fos);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", t);
		root.put("domain", domain);
		root.put("config", config);
		temp.process(root, out);
		out.flush();
		out.close();
	}

	public void generate(Tabel t, Template temp, String outfilename, String outdir)
			throws IOException, TemplateException {
		generate("", t, temp, outfilename, outdir);
	}
}
