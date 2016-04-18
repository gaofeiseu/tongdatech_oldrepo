package com.tongdatech.sys.action;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.service.TreeService;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.ParamsUtil;

/**
 * ��Action      <br>
 * @author xl 
 * @version    2014-4-11 ����09:49:20
 */
public class TreeAction extends ActionSupport {
	

	private static final long serialVersionUID = 9025003429303687566L;

	
	/**�����ڵ�*/
	private String node;
	/**չ�����Ľڵ�*/
	private String expnode;
	
	/**�ڵ����� <p>
	 * root  ��ȡ���ڵ�<p>
	 * child ��ȡ�ӽڵ�  ȱʡֵ<p>
	 * one   ��ȡ���ڵ��Լ���һ��*/
	private String type;
	/**��չʾ��ģʽ����*/
	private String mode;

	private TreeService treeService  = new TreeService();
	
	/**
	 * ������
	 * @return String
	 * @throws Exception
	 */
	public String brch() throws Exception{

		
		ValueStack vs = ActionContext.getContext().getValueStack();//��ȡֵջ
		ParamsUtil p =new ParamsUtil(vs);//�������ö���
		
		/** 
		 * mode:normal ȱʡֵΪֻչʾ������
		 * mode:all չʾȫ���ڵ������ɾ�������صĻ���
		 * */
		List<TreeNode> tree=treeService.getBrchTree(node,type,mode,p);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(tree,response);
		return null;
	}
	
	/**
	 * չ�����ڵ�expnode�Ļ�����
	 * @return String
	 * @throws Exception
	 */
	public String expandToBrch() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)session.get(UserInfo.USER_INFO);
		ValueStack vs = ActionContext.getContext().getValueStack();
		ParamsUtil p =new ParamsUtil(vs);
		Stack<Brch> expand= treeService.getExpandBrchStack(expnode,userInfo);
		List<TreeNode> tree=treeService.recursionBrch(node, type, mode, p, expand);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(tree,response);
		return null;
	}

	/**
	 * @return the node
	 */
	public String getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @param expnode the expnode to set
	 */
	public void setExpnode(String expnode) {
		this.expnode = expnode;
	}

	/**
	 * @return the expnode
	 */
	public String getExpnode() {
		return expnode;
	}
	

}
