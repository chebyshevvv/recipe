package com.zh.common.page;

import lombok.Data;

import java.util.List;

/**
 * 分页返回结果
 */
@Data
public class PageResult {
	public PageResult(long pageNum, long pageSize, long total, long totalPages, List<?> content) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.totalPages = totalPages;
		this.content = content;
	}

	/**
	 * 当前页码
	 */
	private long pageNum;
	/**
	 * 每页数量
	 */
	private long pageSize;
	/**
	 * 记录总数
	 */
	private long total;
	/**
	 * 页码总数
	 */
	private long totalPages;
	/**
	 * 分页数据
	 */
	private List<?> content;
}
