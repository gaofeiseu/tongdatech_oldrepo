package com.tongdatech.question.dao;

import org.apache.log4j.Logger;

import com.tongdatech.question.bean.QuestionBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class QuestionDao extends BaseDao{
	private static Logger log = Logger.getLogger(QuestionDao.class);

	public PageUI query(Pagination pagination, QuestionBean bean,
			UserInfo userinfo) {
		PageUI rs = new PageUI();
		try{
			String sql = "";
			sql = "select a.sn as question_id,to_char(c.status_time,'yyyy-mm-dd hh24:mi:ss') as question_status_time,"
					+"a.question_title,F_PARAMS('Q_STATUS',a.status) as question_status_str,a.question_content,"
					+"a.status as question_status,a.reply_content as question_reply_msg"
					+" from t_sys_question a,t_sys_question_status_time c where a.sn=c.question_id and a.user_id="+userinfo.getUser_id()+" and a.status=c.question_status order by a.sn desc ";
			String listsql = db.queryPageStrOrder(sql, pagination.getStnum(), pagination.getEdnum());
			rs.setRows(db.query(listsql,QuestionBean.class));
			int total = db.count(sql);
			rs.setTotal(total);
			pagination.setTotal(total);
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return rs;
	}

	public AjaxMsg saveQuestion(QuestionBean bean, UserInfo userinfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			int user_id = userinfo.getUser_id();
			String question_title = bean.getQuestion_title();
			String question_content = bean.getQuestion_content();
			String status = "1";//����״̬
			String sql = "";
			sql = "select SEQ_T_SYS_QUESTION.nextval from dual";
			String question_id = db.queryString(sql);
			db.connect();
			db.startTransaction();
			try{
				if(bean.getQuestion_id()==null||"".equals(bean.getQuestion_id())){
					sql = "insert into t_sys_question (sn,user_id,question_title,status,question_content) values ("+question_id+","+user_id+",'"+question_title+"','"+status+"','"+question_content+"')";
					if(db.insert(sql)==1){
						sql = "insert into t_sys_question_status_time (sn,question_id,question_status,status_time) values (SEQ_T_SYS_QUESTION_STATUS_TIME.nextval,"+question_id+",'"+status+"',cast(sysdate as timestamp))";
						if(db.insert(sql)==1){
							db.commit();
							am.setSuccess(true);
							am.setMsg("����ɹ���");
						}else{
							throw new Exception ("����ʧ�ܣ�");
						}
					}else{
						throw new Exception ("����ʧ�ܣ�");
					}
				}else{
					sql = "select count(1) from t_sys_question where status='1' and sn="+bean.getQuestion_id();
					if(db.queryInt(sql)==1){
						sql = "update t_sys_question set question_title='"+question_title+"',question_content='"+question_content+"' where sn="+bean.getQuestion_id();
						if(db.update(sql)==1){
							sql = "update t_sys_question_status_time set status_time=cast(sysdate as timestamp) where question_id="+bean.getQuestion_id();
							if(db.update(sql)==1){
								db.commit();
								am.setSuccess(true);
								am.setMsg("���³ɹ���");
							}else{
								throw new Exception ("����ʧ�ܣ�");
							}
						}else{
							throw new Exception ("����ʧ�ܣ�");
						}
					}else{
						throw new Exception ("ֻ�ܶԡ��ύ״̬������������޸Ĳ�����");
					}
				}
			}
			catch(Exception ex){
				db.rollback();
				throw ex;
			}
			finally{
				db.endTransaction();
				db.disconnect();
			}
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg deleteQuestion(QuestionBean bean, UserInfo userinfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			if(bean.getQuestion_id()==null||"".equals(bean.getQuestion_id())){
				throw new Exception ("���ݵ�����ID�����⣡");
			}
			String sql = "";
			sql = "select count(1) from t_sys_question where sn="+bean.getQuestion_id()+" and status='1' ";
			int count = db.queryInt(sql);
			if(count==1){
				db.connect();
				db.startTransaction();
				try{
					sql = "delete from t_sys_question where sn="+bean.getQuestion_id()+" and status='1' ";
					if(db.delete(sql)==1){
						sql = "delete from t_sys_question_status_time where question_id="+bean.getQuestion_id();
						if(db.delete(sql)==1){
							db.commit();
							am.setSuccess(true);
							am.setMsg("ɾ���ɹ���");
						}else{
							throw new Exception ("ɾ��ʧ�ܣ�");
						}
					}else{
						throw new Exception ("ɾ��ʧ�ܣ�");
					}
				}
				catch(Exception ex){
					db.rollback();
					throw ex;
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}else{
				throw new Exception ("ֻ�����⴦���ύ״̬ʱ��������ɾ����");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public PageUI queryForLock(Pagination pagination, QuestionBean bean,
			UserInfo userinfo) {
		PageUI rs = new PageUI();
		try{
			String sql = "";
			sql = "select a.sn as question_id,to_char(c.status_time,'yyyy-mm-dd hh24:mi:ss') as question_status_time,"
					+"a.question_title,F_PARAMS('Q_STATUS',a.status) as question_status_str,a.question_content,"
					+"a.status as question_status,a.reply_content as question_reply_msg,d.user_name,d.nick_name"
					+" from t_sys_question a,t_sys_question_status_time c,t_sys_user d where d.user_id=a.user_id and a.sn=c.question_id and a.status=c.question_status order by a.sn desc ";
			String listsql = db.queryPageStrOrder(sql, pagination.getStnum(), pagination.getEdnum());
			rs.setRows(db.query(listsql,QuestionBean.class));
			int total = db.count(sql);
			rs.setTotal(total);
			pagination.setTotal(total);
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return rs;
	}

	public AjaxMsg lockQuestion(QuestionBean bean, UserInfo userinfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			if(bean.getQuestion_id()==null||"".equals(bean.getQuestion_id())){
				throw new Exception ("���ݵ�����ID�����⣡");
			}
			String sql = "";
			sql = "select count(1) from t_sys_question where sn="+bean.getQuestion_id()+" and status='1' ";
			int count = db.queryInt(sql);
			if(count==1){
				db.connect();
				db.startTransaction();
				try{
					sql = "update t_sys_question set status='2' where sn="+bean.getQuestion_id();
					if(db.update(sql)==1){
						sql = "insert into t_sys_question_status_time (sn,question_id,question_status,status_time) values (SEQ_T_SYS_QUESTION_STATUS_TIME.nextval,"+bean.getQuestion_id()+",'2',cast(sysdate as timestamp))";
						if(db.insert(sql)==1){
							db.commit();
							am.setSuccess(true);
							am.setMsg("�����ɹ���");
						}else{
							throw new Exception ("����ʧ�ܣ�");
						}
					}else{
						throw new Exception ("����ʧ�ܣ�");
					}
				}
				catch(Exception ex){
					db.rollback();
					throw ex;
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}else{
				throw new Exception ("ֻ�����⴦���ύ״̬ʱ��������������");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg replyQuestion(QuestionBean bean, UserInfo userinfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			if(bean.getQuestion_id()==null||"".equals(bean.getQuestion_id())){
				throw new Exception ("���ݵ�����ID�����⣡");
			}
			String sql = "";
			sql = "select count(1) from t_sys_question where sn="+bean.getQuestion_id()+" and status='2' ";
			int count = db.queryInt(sql);
			if(count==1){
				db.connect();
				db.startTransaction();
				try{
					sql = "update t_sys_question set status='3',reply_content='"+bean.getQuestion_reply_msg()+"' where sn="+bean.getQuestion_id();
					if(db.update(sql)==1){
						sql = "insert into t_sys_question_status_time (sn,question_id,question_status,status_time) values (SEQ_T_SYS_QUESTION_STATUS_TIME.nextval,"+bean.getQuestion_id()+",'3',cast(sysdate as timestamp))";
						if(db.insert(sql)==1){
							db.commit();
							am.setSuccess(true);
							am.setMsg("����ظ��ɹ���");
						}else{
							throw new Exception ("����ظ�ʧ�ܣ�");
						}
					}else{
						throw new Exception ("����ظ�ʧ�ܣ�");
					}
				}
				catch(Exception ex){
					db.rollback();
					throw ex;
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}else{
				throw new Exception ("ֻ�����⴦������״̬ʱ�����������Ϊ�ѽ����");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public PageUI queryForSearch(Pagination pagination, QuestionBean bean,
			UserInfo userinfo) {
		PageUI rs = new PageUI();
		try{
			String wheresql = "";
			if(bean.getSearch_question_str()!=null&&!"".equals(bean.getSearch_question_str())){
				wheresql = " and (instr(a.question_title,'"+bean.getSearch_question_str()+"')>0 or instr(a.question_content,'"+bean.getSearch_question_str()+"')>0) ";
			}
			String sql = "";
			sql = "select a.sn as question_id,to_char(c.status_time,'yyyy-mm-dd hh24:mi:ss') as question_status_time,"
					+"a.question_title,F_PARAMS('Q_STATUS',a.status) as question_status_str,a.question_content,"
					+"a.status as question_status,a.reply_content as question_reply_msg,d.user_name,d.nick_name"
					+" from t_sys_question a,t_sys_question_status_time c,t_sys_user d " +
					"where d.user_id=a.user_id and a.sn=c.question_id and a.status=c.question_status and a.status='3' "+wheresql+" order by a.sn desc ";
			String listsql = db.queryPageStrOrder(sql, pagination.getStnum(), pagination.getEdnum());
			rs.setRows(db.query(listsql,QuestionBean.class));
			int total = db.count(sql);
			rs.setTotal(total);
			pagination.setTotal(total);
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return rs;
	}
}
