package com.tongdatech.sys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserConfig;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.SecurityUtil;

public class UserConfigDao extends BaseDao {
	public PageUI userinfo_query(Pagination pagination, UserConfig userConfig, UserInfo userInfo)
			throws Exception {
		String sql = "SELECT a.user_name AS user_name,"
				+ " a.nick_name       AS user_nick_name,"
				+ " c.brch_name       AS user_brch_name"
				+ " FROM t_sys_user a," + " (SELECT brch_no"
				+ " FROM t_sys_brch" + " START WITH brch_no      ="
				+ userInfo.getBrch_no()
				+ " CONNECT BY prior brch_no=brch_up" + " ) b,t_sys_brch c"
				+ " WHERE a.brch_no=b.brch_no(+)" + " and b.brch_no=c.brch_no"
				+ " AND b.brch_no IS NOT NULL";
		if (!"".equals(userConfig.getUser_name())
				&& userConfig.getUser_name() != null) {
			sql += " and a.user_name like '%" + userConfig.getUser_name()
					+ "%' ";
		}
		if (!"".equals(userConfig.getUser_nick_name())
				&& userConfig.getUser_nick_name() != null) {
			sql += " and a.nick_name like '%" + userConfig.getUser_nick_name()
					+ "%' ";
		}
		if (!"".equals(userConfig.getUser_brch_no())
				&& userConfig.getUser_brch_no() != null) {
			sql += " and a.brch_no =" + userConfig.getUser_brch_no() + "";
		}
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, UserConfig.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public PageUI loadPowerDetailForUser(Pagination pagination,
			UserConfig userConfig) throws Exception {

		String sql = "select c.brch_name brch_name,d.role_name role_name from t_sys_r_user_br a,"
				+ " t_sys_user b,t_sys_brch c,t_sys_role d "
				+ " where a.user_id=b.user_id and b.user_name='"
				+ userConfig.getUser_name()
				+ "' and b.user_flag='1' "
				+ " and a.brch_no=c.brch_no  " + " and d.role_id=a.role_id";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, UserConfig.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg userinfo_add(Pagination pagination, UserConfig userConfig)
			throws Exception {

		String sec_password = SecurityUtil.md5(userConfig.getPassword());
		AjaxMsg msg = new AjaxMsg();
		String sql = "";
		if (!"".equals(userConfig.getUser_nick_name())
				&& userConfig.getUser_nick_name() != null) {
			// 如果用户填写了昵称
			sql = "insert into t_sys_user "
					+ " (user_id,user_name,nick_name,password,brch_no,order_id,user_flag) "
					+ " values " + " (" + " SEQ_T_SYS_USER.nextval," + " '"
					+ userConfig.getUser_name() + "'," + " '"
					+ userConfig.getUser_nick_name() + "'," + " '"
					+ sec_password + "'," + " "
					+ Integer.parseInt(userConfig.getBrch_no()) + "," + " 1,"
					+ " '1'" + " )";
		} else {
			// 如果用户没有填写昵称
			sql = "insert into t_sys_user "
					+ " (user_id,user_name,password,brch_no,order_id,user_flag) "
					+ " values " + " (" + " SEQ_T_SYS_USER.nextval," + " '"
					+ userConfig.getUser_name() + "'," + " '" + sec_password
					+ "'," + " " + Integer.parseInt(userConfig.getBrch_no())
					+ "," + " 1," + " '1'" + " )";
		}
		int resultInt = db.insert(sql);
		if (resultInt == 1) {
			msg.setSuccess(true);
			msg.setMsg("用户" + userConfig.getUser_name() + "新增成功！");
		} else {
			msg.setSuccess(false);
			msg.setMsg("用户" + userConfig.getUser_name() + "新增失败！");
		}

		return msg;
	}

	public AjaxMsg checkUserNameUnique(Pagination pagination,
			UserConfig userConfig) throws Exception {

		String sql = "select * from t_sys_user where user_name='"
				+ userConfig.getCheck_user_name() + "'";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		db.query(listsql, UserConfig.class);
		int total = db.count(sql);
		AjaxMsg msg = new AjaxMsg();
		if (total > 0) {
			msg.setSuccess(false);
			msg.setMsg("该用户名已被占用");
		} else {
			msg.setSuccess(true);
			msg.setMsg("该用户名可用");
		}
		return msg;
	}

	public AjaxMsg userinfo_delete(Pagination pagination, UserConfig userConfig)
			throws Exception {

		AjaxMsg msg = new AjaxMsg();
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		db.startTransaction();
		sql2 = "delete from t_sys_r_user_br where user_id=(select user_id from t_sys_user where user_name='"
				+ userConfig.getDelete_user_name() + "')";
		sql1 = "insert into t_sys_user_dele (select a.*,sysdate from t_sys_user a where user_name='"
				+ userConfig.getDelete_user_name() + "')";
		sql = "delete from t_sys_user where user_name='"
				+ userConfig.getDelete_user_name() + "' ";
		int resultInt2 = db.runSql(sql2);
		int resultInt1 = db.runSql(sql1);
		int resultInt = db.runSql(sql);

		if (resultInt == 1 && resultInt1 == 1) {
			msg.setSuccess(true);
			msg.setMsg("用户" + userConfig.getDelete_user_name() + "删除成功！");
		} else if (resultInt == 1 && resultInt1 != 1) {
			msg.setSuccess(false);
			msg.setMsg("用户" + userConfig.getDelete_user_name() + "删除失败！\n "
					+ "用户数据删除备份表数据插入失败！\n " + "用户数据删除操作成功 \n " + "删除操作即将回滚 \n");
			db.rollback();
		} else if (resultInt != 1 && resultInt1 == 1) {
			msg.setSuccess(false);
			msg.setMsg("用户" + userConfig.getDelete_user_name() + "删除失败！\n "
					+ "用户数据删除备份表数据插入成功！\n " + "用户数据删除操作失败 \n " + "删除操作即将回滚 \n");
			db.rollback();
		} else if (resultInt != 1 && resultInt1 != 1) {
			msg.setSuccess(false);
			msg.setMsg("用户" + userConfig.getDelete_user_name() + "删除失败！\n "
					+ "用户数据删除备份表数据插入失败！\n " + "用户数据删除操作失败 \n " + "删除操作即将回滚 \n");
			db.rollback();
		}
		db.endTransaction();

		return msg;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AjaxMsg userinfo_getUserIDFromUserName(Pagination pagination,
			UserConfig userConfig) throws Exception {

		AjaxMsg msg = new AjaxMsg();
		String sql = "";
		sql = "select * from t_sys_user where user_name='"
				+ userConfig.getUser_name() + "' and user_flag='1'";
		List<Object> resultList = new ArrayList<Object>();
		resultList = db.query(sql);
		if (resultList.isEmpty()) {
			msg.setSuccess(true);
			msg.setMsg("-1");
		} else {
			msg.setSuccess(true);
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap = (Map) resultList.get(0);
			String user_id = String.valueOf(resultMap.get("user_id"));
			String[] s = user_id.split("\\.");
			user_id = s[0];
			msg.setMsg(user_id);
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg userinfo_getBrchNameFromBrchNo(Pagination pagination,
			UserConfig userConfig) throws Exception {

		AjaxMsg msg = new AjaxMsg();
		String sql = "";
		sql = "select * from t_sys_brch where brch_no="
				+ userConfig.getBrch_no() + " and brch_flag='1'";
		List<Object> resultList = new ArrayList<Object>();
		resultList = db.query(sql);
		if (resultList.isEmpty()) {
			msg.setSuccess(true);
			msg.setMsg("-1");
		} else {
			msg.setSuccess(true);
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap = (Map<Object, Object>) resultList.get(0);
			String brch_name = String.valueOf(resultMap.get("brch_name"));
			String[] s = brch_name.split("\\.");
			brch_name = s[0];
			msg.setMsg(brch_name);
		}
		return msg;
	}

	public AjaxMsg userinfo_edit(Pagination pagination, UserConfig userConfig)
			throws Exception {

		System.out.println("brch_no:" + userConfig.getBrch_no());
		System.out.println("user_name:" + userConfig.getUser_name());
		System.out.println("user_nick_name:" + userConfig.getUser_nick_name());
		System.out.println("password:" + userConfig.getPassword());
		System.out.println("user_id:" + userConfig.getUser_id());

		String sec_password = SecurityUtil.md5(userConfig.getPassword());
		AjaxMsg msg = new AjaxMsg();
		String sql = "";

		sql = "UPDATE t_sys_user SET " + " user_name='"
				+ userConfig.getUser_name() + "'," + " nick_name='"
				+ userConfig.getUser_nick_name() + "'," + " password='"
				+ sec_password + "'," + " brch_no=" + userConfig.getBrch_no()
				+ "," + " order_id=1," + " user_flag='1' " + " WHERE user_id="
				+ userConfig.getUser_id();

		int resultInt = db.runSql(sql);
		if (resultInt == 1) {
			msg.setSuccess(true);
			msg.setMsg("用户ID" + userConfig.getUser_id() + "修改成功！");
		} else {
			msg.setSuccess(false);
			msg.setMsg("用户ID" + userConfig.getUser_id() + "修改失败！");
		}

		return msg;

	}

	@SuppressWarnings("unchecked")
	public List<List<String>> initPowerDlgRoleSele(Pagination pagination,
			UserConfig userConfig) throws Exception {

		String sql = "";
		sql = "select to_char(role_id) role_id,role_name from t_sys_role where role_flag='1' order by role_id ";
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		list = db.query(sql);

		List<List<String>> list_result = new ArrayList<List<String>>();
		List<String> list_id = new ArrayList<String>();
		List<String> list_value = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map = list.get(i);
			list_id.add((String) (map.get("role_id")));
			list_value.add((String) (map.get("role_name")));
		}
		list_result.add(list_id);
		list_result.add(list_value);

		return list_result;
	}

	public AjaxMsg userpower_add(Pagination pagination, UserConfig userConfig)
			throws Exception {

		AjaxMsg msg = new AjaxMsg();

		String sql = "select * from t_sys_r_user_br " + " where user_id= "
				+ userConfig.getUser_id() + " and brch_no= "
				+ userConfig.getBrch_no() +
				// " and area_no='"+userConfig.getArea_no()+"' " +
				" and role_id= " + userConfig.getRole_id();
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		db.query(listsql, UserConfig.class);
		int total = db.count(sql);
		if (total > 0) {
			msg.setSuccess(false);
			msg.setMsg("用户权限新增失败！ \n 该用户的该种权限已经存在，请勿重复添加！");
		} else if (total == 0) {
			String insertSQL = "insert into t_sys_r_user_br "
					+ " (sn,user_id,brch_no,area_no,role_id) " + " values "
					+ " (SEQ_T_SYS_R_USER_BR.nextval," + " "
					+ userConfig.getUser_id() + "," + " "
					+ userConfig.getBrch_no() + "," + " '"
					+ userConfig.getArea_no() + "'," + " "
					+ userConfig.getRole_id() + ")";
			int resultInt = db.insert(insertSQL);
			if (resultInt == 1) {
				msg.setSuccess(true);
				msg.setMsg("用户权限新增成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("用户权限新增失败！");
			}
		}
		return msg;
	}

	public AjaxMsg userpower_delete(Pagination pagination, UserConfig userConfig)
			throws Exception {

		AjaxMsg msg = new AjaxMsg();

		// System.out.println("delete_power_username:"+userConfig.getDelete_power_username());
		// System.out.println("delete_power_brchname:"+userConfig.getDelete_power_brchname());
		// System.out.println("delete_power_rolename:"+userConfig.getDelete_power_rolename());
		// System.out.println("area_no:"+userConfig.getArea_no());

		String sql = "";
		sql = "delete from t_sys_r_user_br "
				+ " where user_id=(select user_id from t_sys_user "
				+ " where user_name='" + userConfig.getDelete_power_username()
				+ "' and user_flag='1')"
				+ " and brch_no=(select brch_no from t_sys_brch "
				+ " where brch_name='" + userConfig.getDelete_power_brchname()
				+ "' and brch_flag='1')"
				+ " and role_id=(select role_id from t_sys_role "
				+ " where role_name='" + userConfig.getDelete_power_rolename()
				+ "' and role_flag='1')";
		int resultInt = db.runSql(sql);

		if (resultInt == 1) {
			msg.setSuccess(true);
			msg.setMsg("用户" + userConfig.getDelete_power_username()
					+ "的权限：<br> [部门：" + userConfig.getDelete_power_brchname()
					+ ",角色：" + userConfig.getDelete_power_rolename() + "]删除成功！");
		} else {
			msg.setSuccess(false);
			msg.setMsg("用户" + userConfig.getDelete_power_username()
					+ "的权限：<br> [部门：" + userConfig.getDelete_power_brchname()
					+ ",角色：" + userConfig.getDelete_power_rolename() + "]删除失败！");
		}

		return msg;
	}

	public AjaxMsg userinfo_getBrchNoFromBrchName(Pagination pagination,
			UserConfig userConfig) throws Exception {

		AjaxMsg msg = new AjaxMsg();
		String sql = "";
		sql = "select to_char(brch_no) brch_no from t_sys_brch where brch_name='"
				+ userConfig.getBrch_name() + "' and brch_flag='1'";
		List<Object> resultList = new ArrayList<Object>();
		resultList = db.query(sql);
		if (resultList.isEmpty()) {
			msg.setSuccess(true);
			msg.setMsg("-1");
		} else {
			msg.setSuccess(true);
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap = (Map<Object, Object>) resultList.get(0);
			String brch_no = String.valueOf(resultMap.get("brch_no"));
			msg.setMsg(brch_no);
		}
		return msg;
	}

	public AjaxMsg getRoleIDFromRoleName(Pagination pagination,
			UserConfig userConfig) throws Exception {

		AjaxMsg msg = new AjaxMsg();
		String sql = "";
		sql = "select to_char(role_id) role_id from t_sys_role where role_name='"
				+ userConfig.getRole_name() + "' and role_flag='1'";
		List<Object> resultList = new ArrayList<Object>();
		resultList = db.query(sql);
		if (resultList.isEmpty()) {
			msg.setSuccess(true);
			msg.setMsg("-1");
		} else {
			msg.setSuccess(true);
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap = (Map<Object, Object>) resultList.get(0);
			String role_id = String.valueOf(resultMap.get("role_id"));
			msg.setMsg(role_id);
		}
		return msg;
	}

	public AjaxMsg userpower_edit(Pagination pagination, UserConfig userConfig)
			throws Exception {

		AjaxMsg msg = new AjaxMsg();

		String sql = "select a.* from t_sys_r_user_br a,t_sys_user b "
				+ " where b.user_name='" + userConfig.getPower_edit_user_name()
				+ "' " + " and a.user_id=b.user_id and a.brch_no="
				+ userConfig.getPower_edit_brch_no() + " " + " and a.role_id="
				+ userConfig.getPower_edit_role_id() + " ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		db.query(listsql, UserConfig.class);
		int total = db.count(sql);
		if (total > 0) {
			msg.setSuccess(false);
			msg.setMsg("该用户的该种权限已经存在，请勿重复操作！");
		} else {
			String sql1 = "update t_sys_r_user_br set brch_no="
					+ userConfig.getPower_edit_brch_no() + "," + " role_id="
					+ userConfig.getPower_edit_role_id() + " where "
					+ " user_id=(select user_id from t_sys_user where "
					+ " user_name='" + userConfig.getPower_edit_user_name()
					+ "' and user_flag='1') " + " and brch_no="
					+ userConfig.getOld_power_brch_no() + " and " + " role_id="
					+ userConfig.getOld_power_role_id() + " ";

			int resultInt = db.runSql(sql1);
			if (resultInt == 1) {
				msg.setSuccess(true);
				msg.setMsg("用户权限修改成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("用户权限修改失败！");
			}
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	public List<List<String>> initMainBrchSele(Pagination pagination,
			UserConfig userConfig) throws Exception {
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<List<String>> list_result = new ArrayList<List<String>>();

		String sql = "";
		sql = "SELECT to_char(b.brch_no) brch_no,"
				+ " b.brch_name brch_name,to_char(b.brch_up)"
				+ " brch_up,b.brch_mode brch_mode FROM ((SELECT DISTINCT(b.brch_no)"
				+ " brch_no FROM t_sys_user a,t_sys_r_user_br b WHERE"
				+ " a.user_id=b.user_id AND a.user_name='"
				+ userConfig.getUser_name() + "')UNION"
				+ " (SELECT DISTINCT(brch_no) brch_no FROM t_sys_user"
				+ " WHERE user_name='" + userConfig.getUser_name()
				+ "')) a,t_sys_brch b WHERE"
				+ " a.brch_no=b.brch_no order by b.brch_mode";

		list = db.query(sql);

		if (list.size() == 0) {

		} else {
			List<String> list_brch_no = new ArrayList<String>();
			List<String> list_brch_name = new ArrayList<String>();
			List<String> list_brch_up = new ArrayList<String>();
			List<String> list_brch_mode = new ArrayList<String>();

			for (int i = 0; i < list.size(); i++) {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map = list.get(i);
				list_brch_no.add((String) (map.get("brch_no")));
				list_brch_name.add((String) (map.get("brch_name")));
				list_brch_up.add((String) (map.get("brch_up")));
				list_brch_mode.add((String) (map.get("brch_mode")));
			}

			String min_brch_mode = list_brch_mode.get(0);
			boolean if_need_up = false;
			if (list_brch_mode.size() > 1) {
				for (int i = 1; i < list_brch_mode.size(); i++) {
					if (min_brch_mode.equals(list_brch_mode.get(i))) {
						if_need_up = true;
						break;
					} else {
						continue;
					}
				}
			}

			if (if_need_up) {

			}
			// list_result.add(list_id);
			// list_result.add(list_value);
		}

		return list_result;
	}

	public PageUI excel(Pagination pagination, UserConfig userConfig) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("login_brch_no:"+userConfig.getLogin_brch_no());
		System.out.println("excel_file_name:"+userConfig.getExcelFileName());
		System.out.println("excel_file_title:"+userConfig.getExcelTitleName());
		String sql = "SELECT a.user_name AS user_name,"
			+ " a.nick_name       AS user_nick_name,"
			+ " c.brch_name       AS user_brch_name"
			+ " FROM t_sys_user a," + " (SELECT brch_no"
			+ " FROM t_sys_brch" + " START WITH brch_no      ="
			+ userConfig.getLogin_brch_no()
			+ " CONNECT BY prior brch_no=brch_up" + " ) b,t_sys_brch c"
			+ " WHERE a.brch_no=b.brch_no(+)" + " and b.brch_no=c.brch_no"
			+ " AND b.brch_no IS NOT NULL";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());

		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, UserConfig.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
}
