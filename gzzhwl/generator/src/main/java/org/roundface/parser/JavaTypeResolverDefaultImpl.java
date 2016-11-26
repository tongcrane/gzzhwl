package org.roundface.parser;

/*
 *  Copyright 2005 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.roundface.model.Column;

/**
 * 
 * @author Jeff Butler
 */
public class JavaTypeResolverDefaultImpl {

	protected List<String> warnings;

	protected Properties properties;

	// protected IbatorContext ibatorContext;

	protected boolean forceBigDecimals;

	protected Map<Integer, JdbcTypeInformation> typeMap;

	public JavaTypeResolverDefaultImpl() {
		super();
		properties = new Properties();
		typeMap = new HashMap<Integer, JdbcTypeInformation>();

		typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Long.class.getName())));
		typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Boolean.class.getName())));
		typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", //$NON-NLS-1$
				new FullyQualifiedJavaType(Boolean.class.getName())));
		typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName())));
		typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName())));
		typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", //$NON-NLS-1$
				new FullyQualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", //$NON-NLS-1$
				new FullyQualifiedJavaType(Double.class.getName())));
		typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Double.class.getName())));
		typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", //$NON-NLS-1$
				new FullyQualifiedJavaType(Integer.class.getName())));
		typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName())));
		typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", //$NON-NLS-1$
				new FullyQualifiedJavaType(Float.class.getName())));
		typeMap.put(Types.REF, new JdbcTypeInformation("REF", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Short.class.getName())));
		typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", //$NON-NLS-1$
				new FullyQualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", //$NON-NLS-1$
				new FullyQualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Byte.class.getName())));
		typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName())));
	}

	public void addConfigurationProperties(Properties properties) {
		// this.properties.putAll(properties);
		// forceBigDecimals = StringUtility
		// .isTrue(properties
		// .getProperty(PropertyRegistry.TYPE_RESOLVER_FORCE_BIG_DECIMALS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.ibator.api.JavaTypeResolver#initializeResolvedJavaType
	 * (org.apache.ibatis.ibator.internal.db.ColumnDefinition)
	 */
	public FullyQualifiedJavaType calculateJavaType(Column m) {
		FullyQualifiedJavaType answer;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(m.getDataType());

		if (jdbcTypeInformation == null) {
			switch (m.getDataType()) {
			case Types.DECIMAL:
			case Types.NUMERIC:
				if (m.getDigits() > 0 || m.getSize() > 18 || forceBigDecimals) {
					answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
				} else if (m.getSize() > 9) {
					answer = new FullyQualifiedJavaType(Long.class.getName());
				} else if (m.getSize() > 4) {
					answer = new FullyQualifiedJavaType(Integer.class.getName());
				} else {
					answer = new FullyQualifiedJavaType(Short.class.getName());
				}
				break;

			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getFullyQualifiedJavaType();
		}

		return answer;
	}

	public String calculateJavaTypeName(Column m) {
		FullyQualifiedJavaType t = calculateJavaType(m);
		return t.getFullyQualifiedName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.ibator.api.JavaTypeResolver#calculateJdbcTypeName(org
	 * .apache.ibatis.ibator.api.IntrospectedColumn)
	 */
	public String calculateJdbcTypeName(Column introspectedColumn) {
		String answer;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(introspectedColumn.getDataType());

		if (jdbcTypeInformation == null) {
			switch (introspectedColumn.getDataType()) {
			case Types.DECIMAL:
				answer = "DECIMAL"; //$NON-NLS-1$
				break;
			case Types.NUMERIC:
				answer = "NUMERIC"; //$NON-NLS-1$
				break;
			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getJdbcTypeName();
		}

		return answer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.ibator.api.JavaTypeResolver#setWarnings(java.util.List)
	 */
	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	private static class JdbcTypeInformation {
		private String jdbcTypeName;

		private FullyQualifiedJavaType fullyQualifiedJavaType;

		public JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType) {
			this.jdbcTypeName = jdbcTypeName;
			this.fullyQualifiedJavaType = fullyQualifiedJavaType;
		}

		public String getJdbcTypeName() {
			return jdbcTypeName;
		}

		public FullyQualifiedJavaType getFullyQualifiedJavaType() {
			return fullyQualifiedJavaType;
		}
	}
}
