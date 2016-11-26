package com.gzzhwl.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.gzzhwl.common.service.H2DbService;
import com.gzzhwl.core.data.model.RegionInfo;

@Service
public class H2DbServiceImpl implements H2DbService {

	protected static Logger logger = LoggerFactory.getLogger(H2DbService.class);
	@Autowired
	@Qualifier("h2DataSource")
	private DataSource dataSource;

	@Override
	@Async
	public void saveRegionToMem(List<RegionInfo> alldata) {
		Resource fileResource = new ClassPathResource("region.csv");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			File file = fileResource.getFile();
			FileUtils.writeLines(file, alldata, false);
			conn = dataSource.getConnection();
			pstmt = conn
					.prepareStatement("INSERT INTO zh_region_info (SELECT  * FROM  CSVREAD('" + file.getPath() + "'))");
			int row = pstmt.executeUpdate();
			logger.debug("初始化h2数据库{}条数据", row);
		} catch (IOException e) {
			logger.debug("初始化h2数据库发生错误{}", e.getMessage());
		} catch (SQLException e) {
			logger.debug("初始化h2数据库发生错误{}", e.getMessage());
		} finally {
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
	}

	@Override
	public List<RegionInfo> findRegion(String name, Integer parentRegionId, Integer level) {
		List<RegionInfo> data = Lists.newArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String key = "%" + name + "%";
			pstmt = conn.prepareStatement(
					"select region_id, region_code, region_name, parent_region_id, region_level, region_order from zh_region_info where region_name like ? and parent_region_id = ? and region_level = ?");
			pstmt.setString(1, key);
			pstmt.setInt(2, parentRegionId);
			pstmt.setInt(3, level);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RegionInfo e = new RegionInfo();
				e.setRegionId(rs.getInt("region_id"));
				e.setRegionCode(rs.getString("region_code"));
				e.setParentRegionId(rs.getInt("parent_region_id"));
				e.setRegionLevel(rs.getInt("region_level"));
				e.setRegionName(rs.getString("region_name"));
				e.setRegionOrder(rs.getDouble("region_order"));
				data.add(e);
			}
		} catch (SQLException e) {
			logger.debug("初始化h2数据库发生错误{}", e.getMessage());
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
		return data;
	}

}
