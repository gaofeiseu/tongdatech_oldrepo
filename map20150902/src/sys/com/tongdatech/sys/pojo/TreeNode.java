package com.tongdatech.sys.pojo;

import java.util.List;
import java.util.Map;

/**

 * jQuery 树节点模型       
 * <br>id: node id, which is important to load remote data
 * <br>text: node text to show
 * <br>state: node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
 * <br>checked: Indicate whether the node is checked selected.
 * <br>attributes: custom attributes can be added to a node
 * <br>children: an array nodes defines some children nodes
 * <br>iconCls :节点样式
 * @author xl 
 * @version   2014-3-10 上午10:48:14
 */
public class TreeNode {
    /** String LEAF 叶子节点状态*/
    public static String LEAF="open";
    /** String ROOT 根节点状态*/
    public static String ROOT="closed";
	
	/** String id 节点ID*/
	private String  id;
	/** String text 节点展示文字*/
	private String  text;
	
	/** String state 节点状态  默认closed*/
	private String  state="closed";           
	/** boolean checked 节点是否被选中 默认没有*/
	private boolean  checked=false;
	/** Map<?,?> attributes 节点属性*/
	private Map<?,?>  attributes;
	/** List<TreeNode> children 节点儿子列表*/
	private List<TreeNode>  children;
	/** String iconCls 节点样式*/
	private String iconCls;
	/** Object obj 节点附带对象*/
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
