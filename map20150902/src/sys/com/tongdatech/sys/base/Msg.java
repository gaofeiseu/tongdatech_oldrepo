package com.tongdatech.sys.base;

/**
 * 消息类       <br>
 * @author xl 
 * @version   2014-2-28 下午05:13:22
 */
public abstract class Msg {
	
	/** boolean success 成功与否*/
	private boolean success=false;
	/** String msg 消息内容*/
	private String msg;
	
	/**
	 *默认构造方法 
	 */
	public Msg(){
	}
	/**
	 * 从另外一个Msg对象生成
	 * @param msg
	 */
	public Msg(Msg msg) {
		if(msg!=null){
			this.success=msg.isSuccess();
			this.msg=msg.getMsg();
		}
	}
	/**
	 * 合并2个Msg 对象
	 * @param msg
	 * @return Msg 对象
	 */
	public Msg add(Msg msg){
		if(msg!=null){
			this.success=this.success&&msg.isSuccess();
			this.msg=this.msg+";"+msg.getMsg();
		}
		return this;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
