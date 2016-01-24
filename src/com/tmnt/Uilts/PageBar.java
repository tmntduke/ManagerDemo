package com.tmnt.Uilts;

public class PageBar {

	private int pageSize;// 要显示的条数
	private int recordCount;// 记录的总条数
	private int currentPage;// 表示当前页

	public PageBar(int pageSize, int recordCount, int currentPage) {
		// TODO Auto-generated constructor stub
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		setCurrentPage(currentPage);
	}

	public PageBar(int pageSize, int recordCount) {
		// TODO Auto-generated constructor stub
		this(pageSize, recordCount, 1);
	}

	// �����ҳ��
	public int getPageCount() {
		int size = recordCount / pageSize;
		int mod = recordCount % pageSize;
		if (mod != 0) {
			size++;
		}
		return recordCount == 0 ? 1 : size;
	}

	public int getFromIndex() {
		return (currentPage - 1) * pageSize+1;
	}

	public int getToIndex() {
		return currentPage*pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		int validPage = currentPage <= 0 ? 1 : currentPage;
		validPage = validPage > getPageCount() ? getPageCount() : validPage;
		this.currentPage = validPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
