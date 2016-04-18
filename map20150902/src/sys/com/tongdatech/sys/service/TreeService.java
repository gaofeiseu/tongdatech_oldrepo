package com.tongdatech.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.TreeDao;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.util.ParamsUtil;

public class TreeService extends BaseService{
	
	private TreeDao treeDao = new TreeDao();

	public List<TreeNode> getBrchTree(String node, String type, String mode,
			ParamsUtil p) throws Exception {
		List<TreeNode> rs =new  ArrayList<TreeNode>();
        if("root".equals(type)){
        	getBrchRoot(node, type, mode, p, rs);
        	return rs; 
        	
        }else if("one".equals(type)){
        	TreeNode root=getBrchRoot(node, type, mode, p, rs);
        	List<TreeNode> children =new  ArrayList<TreeNode>();
        	getBrchChildren(root.getId(), type, mode, p,children);
        	root.setChildren(children);
        	return rs; 
        	
        }else{
        	getBrchChildren(node, type, mode, p,rs);
        	return rs;
        	
        }
	
	}

	private TreeNode getBrchRoot(String node, String type, String mode,
			ParamsUtil p,List<TreeNode> rs) throws Exception{
		Brch brch = treeDao.getBrchRoot(node);
		if(brch==null)return null;
		brch.setBrch_name(brch.getBrch_name()+p.getValue("BRCH_FLAG", brch.getBrch_flag()));
		TreeNode treeNode = brch.TreeTrans();
		rs.add(treeNode);
		return treeNode;
		
	}
	private void getBrchChildren (String node, String type, String mode,
			ParamsUtil p,List<TreeNode> rs) throws Exception{
		List<Brch> children=treeDao.getBrchChildren(node,mode);
    	for(Brch brch:children){
    		brch.setBrch_name(brch.getBrch_name()+p.getValue("BRCH_FLAG", brch.getBrch_flag()));
    		rs.add(brch.TreeTrans());
    	}
    	
		
	}
	public Stack<Brch> getExpandBrchStack(String node,UserInfo userInfo) throws Exception{
		Stack<Brch> rs = new Stack<Brch>();
		List<Brch> brchs = treeDao.getParents(node);
		for(Brch brch:brchs){
			rs.push(brch);
			if(brch.getBrch_no()==userInfo.getBrch_no())break; //超出权限的机构不入栈
			
		}
		return rs;
	}
	public List<TreeNode>  recursionBrch(String node, String type, String mode,ParamsUtil p,Stack<Brch> expand) throws Exception{
		List<TreeNode> rs =new ArrayList<TreeNode>();
		List<Brch> brchs=treeDao.getBrchChildren(node,mode);
		for(Brch b:brchs){
			b.setBrch_name(b.getBrch_name()+p.getValue("BRCH_FLAG", b.getBrch_flag()));
			TreeNode tree = b.TreeTrans();
			
			List<TreeNode>  children= null;
			if(expand!=null&&!expand.isEmpty()&&expand.peek().getBrch_no()==b.getBrch_no()){
				expand.pop();
				children=recursionBrch(String.valueOf(b.getBrch_no()),type,mode,p,expand);
			}
			if(expand==null){
				children=recursionBrch(String.valueOf(b.getBrch_no()),type,mode,p,null);//递归调用
			}		
			
			if(children!=null&&children.size()!=0){
				tree.setChildren(children);
			}
			rs.add(tree);
		}
		return rs;
		
	}




}
