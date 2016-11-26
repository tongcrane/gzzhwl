package com.gzzhwl.core.page;

import java.util.ArrayList;
import java.util.List;

public class PageContainer<E> implements Page<E> {
	private long totalCount = 0;
	private int page = 1;
	private int totalPages = 0;
	private int limit = DEFAULT_PAGE_ROWS;
	private boolean hasNextPage = false;
	private boolean hasPrePage = false;
	private List<E> records = null;

	/**
	 * 构造分页器
	 */
	public PageContainer(long totalCount, int page, int totalPages, int limit, boolean hasNextPage, boolean hasPrePage,
			List<E> records) {
		super();
		this.totalCount = totalCount;
		this.page = page - 1;
		this.totalPages = totalPages;
		this.limit = limit;
		this.hasNextPage = hasNextPage;
		this.hasPrePage = hasPrePage;
		this.records = records;
	}

	@Override
	public long getTotal() {
		return totalCount;
	}

	@Override
	public int getPageCount() {
		return totalPages;
	}

	@Override
	public int getCurrent() {
		return page;
	}

	@Override
	public List<E> getRows() {
		return records;
	}

	@Override
	public int getPageSize() {
		return limit;
	}

	@Override
	public boolean isHasNext() {
		return hasNextPage;
	}

	@Override
	public boolean isHasPrev() {
		return hasPrePage;
	}

	public static <E> PageContainer<E> createEmptyPage(int page, int limit) {
		return new PageContainer<E>(0L, page, Page.NO_PAGE, limit, false, false, new ArrayList<E>());
	}

}
