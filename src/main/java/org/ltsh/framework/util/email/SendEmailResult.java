package org.ltsh.framework.util.email;

public class SendEmailResult {
	private Integer status;//发送状态
	private String msg;//反馈信息
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
