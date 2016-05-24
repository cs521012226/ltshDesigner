package org.ltsh.framework.component;


public class Page {
	private int pageIndex = 1;		//当前页数
	private int pageSize = 20;	//每页显示记录数
	
	private int currentRow = 0;	//当前记录数
	private int rowCount = 0;		//总记录数
	
	private int pageCount=0;	//总共页数,一共有多少页
	
	private int firstPage = 1;		//首页
	private int prevPage = 1; 		//上一页
	private int nextPage = 1;		//下一页
	private int lastPage = 1;		//最后一页
	
	private Page(){}
	
	public Page(int pageIndex, int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.countCurrentRow();	//初始化当前记录数
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 初始化（首页、上一页、当前页面、下一页、尾页）信息
	 */
	public void initPage(){
		if(this.rowCount != 0){
			this.pageCount=(int)Math.ceil((rowCount+0.0)/pageSize);
			
			this.lastPage = (this.rowCount/this.pageSize + 1);
			this.nextPage = this.pageIndex + 1;
			this.prevPage = this.pageIndex - 1;
			
			if((this.pageIndex < this.firstPage) || (this.prevPage < this.firstPage)){
				this.pageIndex = this.firstPage;
				this.prevPage = this.firstPage;
				this.nextPage = this.pageIndex + 1;
			}
			if((this.pageIndex > this.lastPage) || (this.nextPage > this.lastPage)){
				this.pageIndex = this.lastPage;
				this.prevPage = this.lastPage - 1;
				this.nextPage = this.lastPage;
			}
			if(this.lastPage==1){
				this.prevPage = 1;
				this.nextPage = 1;
			}
		}
	}
	/** 
	 * @Author: Charles
	 * @Description: 计算出当前记录数目
	 */
	public void countCurrentRow(){
		this.currentRow = (this.pageIndex - 1) * this.pageSize;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 设置总记录数的时候初始化所有数据
	 * @param countRow void: 
	 */
	public void setRowCount(int countRow) {
		this.rowCount = countRow;
		this.initPage();
		this.countCurrentRow();
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.countCurrentRow();
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		StringBuilder returnStr = new StringBuilder(getClass().getSimpleName()+"[");
		returnStr.append("当前页数:" + pageIndex);
		returnStr.append(",页面大小:" + pageSize);
		returnStr.append(",当前第几条:" + currentRow);
		returnStr.append(",总记录数:" + rowCount);
		returnStr.append(",总页数:" + pageCount);
		returnStr.append(",首页:" + firstPage);
		returnStr.append(",上一页:" + prevPage);
		returnStr.append(",下一页:" + nextPage);
		returnStr.append(",尾页:" + lastPage);
		returnStr.append("]");
		return returnStr.toString();
	}
}
