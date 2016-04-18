package com.tongdatech.sys.base;

/**
 * ��Ϣ��       <br>
 * @author xl 
 * @version   2014-2-28 ����05:13:22
 */
public abstract class Msg {
	
	/** boolean success �ɹ����*/
	private boolean success=false;
	/** String msg ��Ϣ����*/
	private String msg;
	
	/**
	 *Ĭ�Ϲ��췽�� 
	 */
	public Msg(){
	}
	/**
	 * ������һ��Msg��������
	 * @param msg
	 */
	public Msg(Msg msg) {
		if(msg!=null){
			this.success=msg.isSuccess();
			this.msg=msg.getMsg();
		}
	}
	/**
	 * �ϲ�2��Msg ����
	 * @param msg
	 * @return Msg ����
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
