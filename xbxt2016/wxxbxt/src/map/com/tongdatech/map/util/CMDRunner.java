package com.tongdatech.map.util;


public class CMDRunner implements Runnable{
	private String cmd_string;
	public CMDRunner(String _cmd_string){
		this.cmd_string = _cmd_string;
	}
	public void run() {
		try{
		    String[] command = { "cmd", "/c", cmd_string };
		    Process ps = Runtime.getRuntime().exec(command );
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
