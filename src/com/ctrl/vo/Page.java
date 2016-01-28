/**
 * @author CTRL 
 * Classname : Page 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.vo;

public class Page {
	private int totalPage; // 总页数
	private int currentPage; // 当前页
	private int totalRecord; // 总记录数
	private int currentRecord = 0; // 当前记录条数
	private int pageSize = 10; // 每页默认记录

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalRecord, int pageSize) {
		if (totalRecord % pageSize == 0)
			this.totalPage = totalRecord / pageSize;
		else
			this.totalPage = totalRecord / pageSize + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentRecord, int pageSize) {
		if (currentRecord % pageSize == 0)
			this.currentPage = currentRecord / pageSize;
		else
			this.currentPage = currentRecord / pageSize + 1;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}

	@Override
	public String toString() {
		return "Page [totalPage=" + totalPage + ", currentPage=" + currentPage
				+ ", totalRecord=" + totalRecord + ", currentRecord="
				+ currentRecord + ", pageSize=" + pageSize + "]";
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
