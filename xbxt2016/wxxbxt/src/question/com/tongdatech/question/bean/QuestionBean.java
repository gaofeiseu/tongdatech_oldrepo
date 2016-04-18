package com.tongdatech.question.bean;

public class QuestionBean {
	private String question_id;
	private String question_title;
	private String question_content;
	private String question_status_time;
	private String question_status;
	private String question_status_str;
	private String question_reply_msg;
	private String user_name;
	private String nick_name;
	private String search_question_str;
	
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public String getQuestion_status_time() {
		return question_status_time;
	}
	public void setQuestion_status_time(String question_status_time) {
		this.question_status_time = question_status_time;
	}
	public String getQuestion_status() {
		return question_status;
	}
	public void setQuestion_status(String question_status) {
		this.question_status = question_status;
	}
	public String getQuestion_status_str() {
		return question_status_str;
	}
	public void setQuestion_status_str(String question_status_str) {
		this.question_status_str = question_status_str;
	}
	public String getQuestion_reply_msg() {
		return question_reply_msg;
	}
	public void setQuestion_reply_msg(String question_reply_msg) {
		this.question_reply_msg = question_reply_msg;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getSearch_question_str() {
		return search_question_str;
	}
	public void setSearch_question_str(String search_question_str) {
		this.search_question_str = search_question_str;
	}
	@Override
	public String toString() {
		return "QuestionBean [question_id=" + question_id + ", question_title="
				+ question_title + ", question_content=" + question_content
				+ ", question_status_time=" + question_status_time
				+ ", question_status=" + question_status
				+ ", question_status_str=" + question_status_str
				+ ", question_reply_msg=" + question_reply_msg + ", user_name="
				+ user_name + ", nick_name=" + nick_name
				+ ", search_question_str=" + search_question_str + "]";
	}
}
