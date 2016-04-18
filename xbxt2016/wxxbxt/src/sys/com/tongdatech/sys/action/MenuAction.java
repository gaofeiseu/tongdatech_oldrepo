package com.tongdatech.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.bean.Menu;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.service.MenuService;
import com.tongdatech.sys.util.JsonUtil;


/**
 * 菜单Action<br>
 * @author xl 
 * @version    2014-4-11 上午09:40:01
 */

public class MenuAction extends ActionSupport implements ModelDriven<Menu>{



	private static final long serialVersionUID = -8005825987906832672L;
	private Menu menu = new Menu();
	MenuService menuService = new MenuService();
	List<Menu>  menuList;
	Map<String,Object> menuMaps ;
	
	
	/** int role_id 角色查询*/
	private int role_id;
	/** String[] areas 地区数组*/
	private String[] areas;
	/** String treePoint 拖动位置信息*/
	private String treePoint;
	

	/**
	 * 菜单初始化
	 * @return String
	 * @throws Exception
	 */
	public String init() throws Exception{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
		menuList=menuService.getMainMenu(userInfo);
		menuMaps=new HashMap<String, Object>();
		Gson gson = new Gson();
		for(Menu m:menuList){
			List<TreeNode> tmp=menuService.getChildMenus(userInfo,m.getMenu_id());
			menuMaps.put(String.valueOf(m.getMenu_id()), gson.toJson(tmp));
		}
		return "init";
	}
	
	/**
	 * 进入菜单配置界面
	 * @return String
	 */
	public String conf(){
		return "conf";
	}
	

    /**
     * 菜单树
     * @return String
     * @throws Exception
     */
    public String tree() throws Exception{
    	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
    	List<TreeNode> rs=menuService.getChildMenusNoAuth(userInfo,0);
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
    }

    /**
     * 菜单角色权限树
     * @return String
     * @throws Exception
     */
    public String treeChecked() throws Exception{
    	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
    	List<TreeNode> rs=menuService.getChildMenusChecked(userInfo,0,role_id);
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
    }

	/**
	 * 菜单点击日志
	 * @return String
	 * @throws Exception
	 */
	public String menuLog() throws Exception{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
		menuService.getMenuLog(userInfo, menu);
		return null;
	}

	/**
	 * 菜单普通保存
	 * @return String
	 * @throws Exception
	 */
	public String menuSave() throws Exception{
		AjaxMsg rs = menuService.menuSave(menu);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
	}
	
	/**
	 * 菜单姓名检查
	 * @return String
	 * @throws Exception
	 */
	public String nameCheck() throws Exception{
		AjaxMsg rs = menuService.nameCheck(menu);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
	}
	
	/**
	 * 菜单地区权限树
	 * @return String
	 * @throws Exception
	 */
	public String treeArea() throws Exception{
    	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
		List<TreeNode> rs =menuService.getAreaChecked(userInfo.getArea_no(),menu.getMenu_id());
		
    	
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
    }
	
	public String areaSave() throws Exception{
    		
		AjaxMsg rs =menuService.areaSave(areas,menu.getMenu_id());
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
    	
    }
	
	/**
	 * 拖动树并保存
	 * @return String
	 * @throws Exception
	 */
	public String dragTree()throws Exception{
		AjaxMsg rs =menuService.dragTree(menu,treePoint);
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Menu getModel() {
		return menu;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * @param menuService the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * @return the menuList
	 */
	public List<Menu> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the menuMaps
	 */
	public Map<String, Object> getMenuMaps() {
		return menuMaps;
	}

	/**
	 * @param menuMaps the menuMaps to set
	 */
	public void setMenuMaps(Map<String, Object> menuMaps) {
		this.menuMaps = menuMaps;
	}

	/**
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	/**
	 * @return the areas
	 */
	public String[] getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(String[] areas) {
		this.areas = areas;
	}

	/**
	 * @return the treePoint
	 */
	public String getTreePoint() {
		return treePoint;
	}

	/**
	 * @param treePoint the treePoint to set
	 */
	public void setTreePoint(String treePoint) {
		this.treePoint = treePoint;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	



}