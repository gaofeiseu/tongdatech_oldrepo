package com.tongdatech.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.Area;
import com.tongdatech.sys.bean.Menu;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.MenuDao;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.pojo.TreePoint;

public class MenuService extends BaseService {
	
		private MenuDao menuDao = new MenuDao();

		public List<Menu> getMainMenu(UserInfo userInfo) throws Exception {
			return menuDao.getMainMenu(userInfo) ;
		}

		public List<TreeNode> getChildMenus(UserInfo userInfo, int menu_id) throws Exception {
			List<TreeNode> rs =new ArrayList<TreeNode>();
			List<Menu> menus=menuDao.getChildMenu(userInfo,menu_id);
			for(Menu m:menus){
				TreeNode tree = m.TreeTrans();
				List<TreeNode>  children=getChildMenus(userInfo,m.getMenu_id());//递归调用
				
				if(children!=null&&children.size()!=0){
					tree.setChildren(children);
				}else{
					tree.setState(TreeNode.LEAF);
					tree.setChildren(null);
				}
				rs.add(tree);
			}
			return rs;
		}
		
		
		public List<TreeNode> getChildMenusNoAuth(UserInfo userInfo, int menu_id) throws Exception {
			List<TreeNode> rs =new ArrayList<TreeNode>();
			List<Menu> menus=menuDao.getChildMenuNoAuth(userInfo,menu_id);
			for(Menu m:menus){
				TreeNode tree = m.TreeTrans();
				List<TreeNode>  children=getChildMenusNoAuth(userInfo,m.getMenu_id());//递归调用
				
				if(children!=null&&children.size()!=0){
					tree.setChildren(children);
				}else{
					tree.setState(TreeNode.LEAF);
					tree.setChildren(null);
				}
				rs.add(tree);
			}
			return rs;
		}
		
		
		public List<TreeNode> getChildMenusChecked(UserInfo userInfo, int menu_id, int role_id) throws Exception {
			List<TreeNode> rs =new ArrayList<TreeNode>();
			List<Menu> menus=menuDao.getChildMenuChecked(userInfo,menu_id,role_id);
			for(Menu m:menus){
				TreeNode tree = m.TreeTrans();
				List<TreeNode>  children=getChildMenusChecked(userInfo,m.getMenu_id(),role_id);//递归调用
				
				if(children!=null&&children.size()!=0){
					tree.setChildren(children);
				}else{
					tree.setState(TreeNode.LEAF);
					tree.setChildren(null);
				}
				rs.add(tree);
			}
			return rs;
		}
		public int getMenuLog(UserInfo userInfo,Menu menu) throws Exception{
			return menuDao.getMenuLog(userInfo, menu);
		}

		public AjaxMsg menuSave(Menu menu) throws Exception {
			return menuDao.menuSave(menu);
		}

		public AjaxMsg nameCheck(Menu menu) {
			return menuDao.nameCheck(menu);
		}

		public List<TreeNode> getChildAreaChecked(String area_no,
				int menu_id) throws Exception {
			List<TreeNode> rs =new ArrayList<TreeNode>();
			List<Area> areas=menuDao.getChildAreaChecked(area_no,menu_id);
			for(Area a:areas){
				TreeNode tree = a.TreeTrans();
				List<TreeNode>  children=getChildAreaChecked(a.getArea_no(),menu_id);//递归调用
				
				if(children!=null&&children.size()!=0){
					tree.setChildren(children);
				}else{
					tree.setState(TreeNode.LEAF);
					tree.setChildren(null);
				}
				rs.add(tree);
			}
			return rs;
		}

		public TreeNode getRootAreaChecked(String area_no, int menu_id) throws Exception {
			Area area = menuDao.getRootAreaChecked(area_no,menu_id);
			return area.TreeTrans();
		}

		public List<TreeNode> getAreaChecked(String area_no, int menu_id) throws Exception {
			TreeNode root    = getRootAreaChecked(area_no,  menu_id);
			root.setState(TreeNode.LEAF);
	    	List<TreeNode> children = getChildAreaChecked(area_no,  menu_id);
	    	root.setChildren(children);
	    	List<TreeNode> rs =new ArrayList<TreeNode>();
	    	rs.add(root);
	    	return rs;
			
		}

		public AjaxMsg areaSave(String[] areas, int menu_id) throws Exception {
			// TODO Auto-generated method stub
			return menuDao.areaSave( areas,  menu_id);
		}

		public AjaxMsg dragTree(Menu menu, String treePoint) throws Exception {
			AjaxMsg rs = null;
            if(treePoint.equals(TreePoint.append.getValue())){
            	rs = menuDao.menuChange(menu);
            }else if(treePoint.equals(TreePoint.top.getValue())){
            	rs = menuDao.menuOrder(menu.getMenu_id(),menu.getMenu_parent(),-1);
            }else{
            	rs = menuDao.menuOrder(menu.getMenu_id(),menu.getMenu_parent(),1);
            }
			return rs;
		}
}


