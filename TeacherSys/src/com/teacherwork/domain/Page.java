package com.teacherwork.domain;

public class Page {
	
	private int currentPage;
	private int lineSize;
	private int allRecord;
	private int pageSize;
	
	public Page(){
		this.currentPage = 1;
		this.lineSize = 10;
	}
	
	public Page(int allRecord,int lineSize){
		this.currentPage = 1;
		this.lineSize = lineSize;
		this.allRecord = allRecord;
		update();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLineSize() {
		return lineSize;
	}
	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}
	public int getAllRecord() {
		return allRecord;
	}
	public void setAllRecord(int allRecord) {
		this.allRecord = allRecord;
		update();
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if(currentPage <= 0 ){
			this.currentPage= 1;
		} else if(currentPage >= pageSize) {
			this.currentPage= pageSize ;
		}
	}

	public void update(){
		this.pageSize = (this.allRecord + this.lineSize -1) / this.lineSize ;
		if(this.pageSize == 0){
			this.pageSize = 1 ;
		}
	}
	
}