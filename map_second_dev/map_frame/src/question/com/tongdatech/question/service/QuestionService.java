package com.tongdatech.question.service;

import com.tongdatech.question.bean.QuestionBean;
import com.tongdatech.question.dao.QuestionDao;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class QuestionService {
	private QuestionDao dao = new QuestionDao();

	public PageUI query(Pagination pagination, QuestionBean bean,
			UserInfo userinfo) {
		return dao.query(pagination,bean,userinfo);
	}

	public AjaxMsg saveQuestion(QuestionBean bean, UserInfo userinfo) {
		return dao.saveQuestion(bean,userinfo);
	}

	public AjaxMsg deleteQuestion(QuestionBean bean, UserInfo userinfo) {
		return dao.deleteQuestion(bean,userinfo);
	}

	public PageUI queryForLock(Pagination pagination, QuestionBean bean,
			UserInfo userinfo) {
		return dao.queryForLock(pagination,bean,userinfo);
	}

	public AjaxMsg lockQuestion(QuestionBean bean, UserInfo userinfo) {
		return dao.lockQuestion(bean,userinfo);
	}

	public AjaxMsg replyQuestion(QuestionBean bean, UserInfo userinfo) {
		return dao.replyQuestion(bean,userinfo);
	}

	public PageUI queryForSearch(Pagination pagination, QuestionBean bean,
			UserInfo userinfo) {
		return dao.queryForSearch(pagination,bean,userinfo);
	}
	
}
