package org.roundface.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.roundface.model.Column;
import org.roundface.model.Tabel;

public class ConnetionUtils {
	public static Connection getConnetion() throws Exception {
		String diver = ProReader.getInstance().getProperty("jdbc.driver");
		String url = ProReader.getInstance().getProperty("jdbc.url");
		String user = ProReader.getInstance().getProperty("jdbc.username");
		String password = ProReader.getInstance().getProperty("jdbc.password");
		Class.forName(diver);
		return DriverManager.getConnection(url, user, password);
	}

	public static List<Tabel> getTables(Connection con, String[] types, String table) throws Exception {
		List<Tabel> l = new ArrayList<Tabel>();
		DatabaseMetaData dm = con.getMetaData();
		String catalog = null;
		String schema = null;

		ResultSet rs = dm.getTables(catalog, schema, table, types);
		while (rs.next()) {
			String tabelName = rs.getString("TABLE_NAME");
			String cat = rs.getString("TABLE_CAT");
			String schem = rs.getString("TABLE_SCHEM");
			String remarks = rs.getString("REMARKS");

			Tabel t = new Tabel();
			t.setTabelName(tabelName);
			t.setCat(cat);
			t.setSchem(schem);
			t.setCnName(remarks);

			ResultSet rspk = dm.getPrimaryKeys(t.getCat(), t.getSchem(), t.getTabelName());
			List<Column> pkColumn = getPkColumns(rspk, dm, t);
			t.setPkCloums(pkColumn);

			ResultSet rsc = dm.getColumns(t.getCat(), t.getSchem(), t.getTabelName(), null);
			List<Column> columns = getCloums(rsc);
			t.setCloums(columns);
			l.add(t);

		}
		return l;
	}

	public static List<Column> getPkColumns(ResultSet rs, DatabaseMetaData dm, Tabel t) throws Exception {
		List<Column> l = new ArrayList<Column>();
		while (rs.next()) {
			String cloumName = rs.getString("COLUMN_NAME");
			ResultSet rsc = dm.getColumns(t.getCat(), t.getSchem(), t.getTabelName(), cloumName);
			if (rsc.next())
				l.add(getCloum(rsc));
		}
		return l;
	}

	public static List<Column> getFkCloums(ResultSet rs, DatabaseMetaData dm, Tabel t) throws Exception {
		List<Column> l = new ArrayList<Column>();
		while (rs.next()) {
			String cloumName = rs.getString("PKCOLUMN_NAME");
			ResultSet rsc = dm.getColumns(t.getCat(), t.getSchem(), t.getTabelName(), cloumName);
			if (rsc.next())
				l.add(getCloum(rsc));
		}
		return l;
	}

	public static List<Column> getCloums(ResultSet rs) throws Exception {
		List<Column> l = new ArrayList<Column>();
		while (rs.next()) {
			l.add(getCloum(rs));
		}
		return l;
	}

	public static Column getCloum(ResultSet rs) throws Exception {
		Column c = new Column();
		String cloumName = rs.getString("COLUMN_NAME");
		c.setCloumName(cloumName);
		int nullable = rs.getInt("NULLABLE");

		c.setNotNull(nullable == 0);
		int size = rs.getInt("COLUMN_SIZE");
		c.setSize(size);
		int dataType = rs.getInt("DATA_TYPE");
		c.setDataType(dataType);
		int digits = rs.getInt("DECIMAL_DIGITS");
		c.setDigits(digits);
		String remarks = rs.getString("REMARKS");
		c.setCnName(remarks);
		return c;
	}

}
