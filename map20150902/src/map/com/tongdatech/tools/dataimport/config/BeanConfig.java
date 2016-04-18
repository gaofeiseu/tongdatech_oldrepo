package com.tongdatech.tools.dataimport.config;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public  class BeanConfig implements Serializable  {

	private static final long serialVersionUID = 2301300587643074256L;
	
	public static final String CHAINS_RENDER = "render";
    public static final String CHAINS_CHOOSE = "choose";
    
    protected String name;
    protected String className;
    protected String title;
    protected boolean params;
    protected Set<String> types;
    protected List<String> chains;
    
    
    
    

	BeanConfig(String name, String className, String title,
			Set<String> types,boolean params) {
		super();
		this.name = name;
		this.className = className;
		this.title = title;
		this.types = types;
		this.params = params;
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanConfig other = (BeanConfig) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Set<String> getTypes() {
		return types;
	}
	public void setTypes(Set<String> types) {
		this.types = types;
	}
	public List<String> getChains() {
		return chains;
	}
	public void setChains(List<String> chains) {
		this.chains = chains;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the params
	 */
	public boolean isParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(boolean params) {
		this.params = params;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BeanConfig [name=" + name + ", className=" + className
				+ ", title=" + title + ", params=" + params + ", types="
				+ types + ", chains=" + chains + "]";
	}


    
    
    
	
	
}
