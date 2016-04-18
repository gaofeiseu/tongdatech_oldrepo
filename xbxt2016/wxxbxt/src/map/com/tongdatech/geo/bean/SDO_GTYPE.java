package com.tongdatech.geo.bean;

public class SDO_GTYPE {
	public enum ENUM_SDO_GTYPE{
		Unknow(2000),
		Single_point(2001),
		Single_ployline(2002),
		Single_ploygon(2003),
		Collection(2004),
		Mutli_point(2005),
		Mutli_ployline(2006),
		Mutli_ploygon(2007);
		private int _value;
	    private ENUM_SDO_GTYPE(int value)
	    {
	        _value = value;
	    }
	    public int value()
	    {
	        return _value;
	    }
	    @Override
	    public String toString() {
	    	// TODO Auto-generated method stub
	    	return super.toString();
	    }
	}
}
