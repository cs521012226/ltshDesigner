package org.ltsh.framework.common.vo;

/**
 * @author Charles
 * 用来回复给客户端信息的类
 *
 */
public class MessageResult {

	public static final String MESSAGE_RESULT = "messageResult";
	
	private String msgContent;	// 返回提示内容
	private int state;			// 返回结果状态
	private String returnUrl;	// 返回特定的URL
	
	public MessageResult(){
		//无参构造
	}
	
	public MessageResult(String msgContent){
		this.msgContent = msgContent;
	}
	
	public MessageResult(String msgContent, int state){
		this(msgContent);
		this.state = state;
	}
	
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	public String toJsonString() {
		return "{\"state\":" + getState() + ",\"msgContent\":\"" + getMsgContent() + "\",\"returnUrl\":\""
				+ getReturnUrl() + "\"}";
	}
}
