package com.gzzhwl.api.line.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.rest.exception.RestException;

public interface LineService {
	/**
	 * 添加线路
	 * @param accountId
	 * @param departureCode
	 * @param destinationCode
	 * @return
	 * @throws RestException
	 */
	public boolean saveLine(String accountId, String departureCode, String destinationCode)
			throws RestException;

	/**
	 * 添加线路
	 * @param accountId
	 * @param driverInfoId
	 * @param departureCode
	 * @param destinationCode
	 * @return
	 * @throws RestException
	 */
	public boolean saveLine(String accountId, String driverInfoId, String departureCode, String destinationCode)
			throws RestException;
	
	/**
	 * 修改线路
	 * @param lineInfoId
	 * @param departureCode
	 * @param destinationCode
	 * @return
	 */
	public boolean updateLine(String lineInfoId,String driverInfoId,String accountId,String departureCode,String destinationCode);
	
	/**
	 * 删除线路
	 * @param lineInfoId
	 * @return
	 */
	public boolean delLine(String lineInfoId,String driverInfoId,String accountId);
	
	/**
	 * 获取线路列表
	 * @param accountId
	 * @param driverInfoId
	 * @return
	 */
	public List<Map<String, Object>> getLineList(String accountId,String driverInfoId);
	
	/**
	 * 获取热门线路
	 * @param accountId
	 * @return
	 */
	public List<Map<String, Object>> getHotLineList(String accountId,Integer lineCount);
	
	/**
	 * 获取临时热门线路
	 * @param count 获取临时线路条数
	 * @return
	 */
	public List<Map<String, Object>> getTmpHotLineList(Integer count);
	
}
