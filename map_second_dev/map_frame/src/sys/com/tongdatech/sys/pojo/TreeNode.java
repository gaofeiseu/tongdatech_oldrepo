package com.tongdatech.sys.pojo;

import java.util.List;
import java.util.Map;

/**

 * jQuery ���ڵ�ģ��       
 * <br>id: node id, which is important to load remote data
 * <br>text: node text to show
 * <br>state: node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
 * <br>checked: Indicate whether the node is checked selected.
 * <br>attributes: custom attributes can be added to a node
 * <br>children: an array nodes defines some children nodes
 * <br>iconCls :�ڵ���ʽ
 * @author xl 
 * @version   2014-3-10 ����10:48:14
 */
public class TreeNode {
    /** String LEAF Ҷ�ӽڵ�״̬*/
    public static String LEAF="open";
    /** String ROOT ���ڵ�״̬*/
    public static String ROOT="closed";
	
	/** String id �ڵ�ID*/
	private String  id;
	/** String text �ڵ�չʾ����*/
	private String  text;
	
	/** String state �ڵ�״̬  Ĭ��closed*/
	private String  state="closed";           
	/** boolean checked �ڵ��Ƿ�ѡ�� Ĭ��û��*/
	private boolean  checked=false;
	/** Map<?,?> attributes �ڵ�����*/
	private Map<?,?>  attributes;
	/** List<TreeNode> children �ڵ�����б�*/
	private List<TreeNode>  children;
	/** String iconCls �ڵ���ʽ*/
	private String iconCls;
	/** Object obj �ڵ㸽������*/
	private Object obj;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/**
	 * @return the attributes
	 */
	public Map<?, ?> getAttributes() {
		return attributes;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<?, ?> attributes) {
		this.attributes = attributes;
	}
	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}
	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JqTreeNode [id=" + id + ", text=" + text + ", state=" + state
				+ ", checked=" + checked + ", attributes=" + attributes
				+ ", children=" + children + ", iconCls=" + iconCls + ", obj="
				+ obj + "]";
	}
	
	





}
