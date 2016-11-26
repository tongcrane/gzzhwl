package com.gzzhwl.core.page;

import java.util.List;

/**
 * 
 * 分页器接口。
 * 
 */
public interface Page<E> {
	/** 默认页码 */
	public final static int NO_PAGE = 1;
	/** 最小每页显示数量 */
	public static final int MIN_PAGE_ROWS = 1;
	/** 默认每页显示数量 */
	public static final int DEFAULT_PAGE_ROWS = 10;

	/**
	 * 取总记录数
	 */
	public long getTotal();

	/**
	 * 取总页数.
	 */
	public int getPageCount();

	/**
	 * 得到当前页码
	 */
	public int getCurrent();

	/**
	 * 得到该页的数据
	 */
	public List<E> getRows();

	/**
	 * 得到每页行数
	 */
	public int getPageSize();

	/**
	 * 是否有下一页
	 */
	public boolean isHasNext();

	/**
	 * 是否有上一页
	 */
	public boolean isHasPrev();
}
