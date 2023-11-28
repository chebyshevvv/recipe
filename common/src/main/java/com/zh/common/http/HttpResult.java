package com.zh.common.http;

import lombok.Data;

/**
 * HTTP结果封装
 */
@Data
public class HttpResult<T> {

	private int code = 200;
	private String msg = "操作成功";
	private T data;
	
	public static HttpResult<String> error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static HttpResult<String> error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static HttpResult<String> error(int code, String msg) {
		HttpResult<String> r = new HttpResult<>();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static HttpResult<String> ok(String msg) {
		HttpResult<String> r = new HttpResult<>();
		r.setMsg(msg);
		return r;
	}
	
	public static <T>HttpResult<T> ok(T data) {
		HttpResult<T> r = new HttpResult<>();
		r.setData(data);
		return r;
	}
	
	public static HttpResult<?> ok() {
		return new HttpResult<>();
	}
	
}
