package com.unistrong.geotsd.datasource.data;

import java.util.List;

/**
 *  分页数据
 * @author zc.shen
 * @created 2018-06-27
 */
public class Paging<T> {

	private int pageNum;//当前页码
	private int pageSize;//页数据大小
	private long total;//数据个数
	private int count;//总页数
	private List<T> list;

	public Paging(){
		this(-1,0,0,0,null);

	}

	public Paging(int pageNum, int pageSize, long total, int count, List<T> list) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.count = count;
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotal() {
		return total;
	}

	public int getCount() {
		return count;
	}

	public List<T> getList() {
		return list;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}