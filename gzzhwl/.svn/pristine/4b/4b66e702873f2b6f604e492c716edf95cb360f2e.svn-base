package com.gzzhwl.rest.springmvc.model;

import com.gzzhwl.core.page.Page;

public class PageParameter {
	private int pageIndex = Page.NO_PAGE;
	private int pageSize = Page.DEFAULT_PAGE_ROWS;

	private int maxSize = 50;

	public void setPageIndex(int pageIndex) {
		if (pageIndex > Page.NO_PAGE) {
			this.pageIndex = pageIndex;
		} else {
			this.pageIndex = Page.NO_PAGE;
		}
	}

	public void setPageSize(int pageSize) {
		if (pageSize > Page.MIN_PAGE_ROWS) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = Page.MIN_PAGE_ROWS;
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public PageParameter(String pageIndex, String pageSize) {
		try {
			int current = Integer.parseInt(pageIndex);
			current = current + 1;
			this.setPageIndex(current);
		} catch (NumberFormatException e) {
		}
		try {
			int size = Integer.parseInt(pageSize);
			if (size > maxSize) {
				size = maxSize;
			}
			this.setPageSize(size);
		} catch (NumberFormatException e) {
		}
	}

	public PageParameter(int pageIndex, int pageSize) {
		this.setPageIndex(pageIndex);
		this.setPageSize(pageSize);
	}

}
