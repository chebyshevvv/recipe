package com.zh.common.page;

import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {
	/**
	 * 当前页码
	 */
	private int pageNum = 1;
	/**
	 * 每页数量
	 */
	private int pageSize = 10;

}
