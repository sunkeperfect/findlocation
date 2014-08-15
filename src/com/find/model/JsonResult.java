package com.find.model;

public class JsonResult {
	public JsonResult() {
		status = 10000;
		msg = "服务器处理异常！";
		value = null;
	}

	int status;
	String msg;
	Object value;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
