package com.tongdatech.business.bean;

import com.tongdatech.sys.annotation.PageParam;

public class RemindBean {

	@PageParam
	private String query_type;
	@PageParam
	private String user_root_brch;
	@PageParam
	private String CUSTNAME;
	@PageParam
	private String SJ;
	@PageParam
	private String DH;
	@PageParam
	private String ACCOUNT_NO;
	@PageParam
	private String CHUXU_TYPE;
	@PageParam
	private String MONEY_FINAL;
	@PageParam
	private String STATUS;
	@PageParam
	private String OPEN_OFFICE_CODE;
	@PageParam
	private String TRADE_DATE;
	@PageParam
	private String DAOQI_DATE;
	@PageParam
	private String CUST_P_ID;
	@PageParam
	private String CONTRACTNO;
	@PageParam
	private String MONEY;
	@PageParam
	private String BAOXIAN_NO;
	@PageParam
	private String BXGS;
	@PageParam
	private String TYPE;
	@PageParam
	private String PASS_NO;
	@PageParam
	private String phone;
	@PageParam
	private String tel;
	@PageParam
	private String address;
	@PageParam
	private String BAOFEI;
	@PageParam
	private String ACCOUNT_ORG;
	@PageParam
	private String SHUHUI_TAG;
	@PageParam
	private String POL_STAT_FLAG;
	@PageParam
	private String POL_FINAL_DATE;
	@PageParam
	private String PRODUCT_NAME;
	@PageParam
	private String PSN_18;
	@PageParam
	private String ENDDATE;
	
	
	
	
	
	
	
	public String getCONTRACTNO() {
		return CONTRACTNO;
	}

	public void setCONTRACTNO(String cONTRACTNO) {
		if(cONTRACTNO.length()>4){
			CONTRACTNO = cONTRACTNO.substring(0, cONTRACTNO.length()-4)+"****";
		}else{
			CONTRACTNO = cONTRACTNO;
		}
	}

	public String getUser_root_brch() {
		return user_root_brch;
	}

	public void setUser_root_brch(String user_root_brch) {
		this.user_root_brch = user_root_brch;
	}

	public String getCUSTNAME() {
		return CUSTNAME;
	}

	public void setCUSTNAME(String cUSTNAME) {
		cUSTNAME = cUSTNAME.trim();
		if(cUSTNAME.length()==2){
			CUSTNAME = cUSTNAME.substring(0, 1)+"*";
		}
		else if(cUSTNAME.length()==3){
			CUSTNAME = cUSTNAME.substring(0, 1)+"**";
		}
		else if(cUSTNAME.length()==4){
			CUSTNAME = cUSTNAME.substring(0, 2)+"**";
		}
		else{
			CUSTNAME = cUSTNAME;
		}
	}

	public String getSJ() {
		return SJ;
	}

	public void setSJ(String sJ) {
		SJ = sJ;
	}

	public String getDH() {
		return DH;
	}

	public void setDH(String dH) {
		DH = dH;
	}

	public String getACCOUNT_NO() {
		return ACCOUNT_NO;
	}

	public void setACCOUNT_NO(String aCCOUNT_NO) {
		if(aCCOUNT_NO.length()>=4){
			ACCOUNT_NO = aCCOUNT_NO.substring(0,aCCOUNT_NO.length()-4)+"****";
		}else{
			ACCOUNT_NO = aCCOUNT_NO;
		}
	}

	public String getCHUXU_TYPE() {
		return CHUXU_TYPE;
	}

	public void setCHUXU_TYPE(String cHUXU_TYPE) {
		CHUXU_TYPE = cHUXU_TYPE;
	}

	public String getMONEY_FINAL() {
		return MONEY_FINAL;
	}

	public void setMONEY_FINAL(String mONEY_FINAL) {
		if(mONEY_FINAL.length()>1){
			if("-".equals(mONEY_FINAL.substring(0, 1))){
				MONEY_FINAL = "-"+"*"+mONEY_FINAL.substring(2, mONEY_FINAL.length());
			}else{
				MONEY_FINAL = "*"+mONEY_FINAL.substring(1, mONEY_FINAL.length());
			}
		}else{
			MONEY_FINAL = mONEY_FINAL;
		}
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getOPEN_OFFICE_CODE() {
		return OPEN_OFFICE_CODE;
	}

	public void setOPEN_OFFICE_CODE(String oPEN_OFFICE_CODE) {
		OPEN_OFFICE_CODE = oPEN_OFFICE_CODE;
	}

	public String getTRADE_DATE() {
		return TRADE_DATE;
	}

	public void setTRADE_DATE(String tRADE_DATE) {
		TRADE_DATE = tRADE_DATE;
	}

	public String getDAOQI_DATE() {
		return DAOQI_DATE;
	}

	public void setDAOQI_DATE(String dAOQI_DATE) {
		DAOQI_DATE = dAOQI_DATE;
	}

	public String getCUST_P_ID() {
		return CUST_P_ID;
	}

	public void setCUST_P_ID(String cUST_P_ID) {
		CUST_P_ID = cUST_P_ID;
	}

	public String getMONEY() {
		return MONEY;
	}

	public void setMONEY(String mONEY) {
		if(mONEY.length()>1){
			if("-".equals(mONEY.substring(0, 1))){
				MONEY = "-"+"*"+mONEY.substring(2, mONEY.length());
			}else{
				MONEY = "*"+mONEY.substring(1, mONEY.length());
			}
		}else{
			MONEY = mONEY;
		}
	}

	public String getBAOXIAN_NO() {
		return BAOXIAN_NO;
	}

	public void setBAOXIAN_NO(String bAOXIAN_NO) {
		if(bAOXIAN_NO.length()>4){
			BAOXIAN_NO = bAOXIAN_NO.substring(0, bAOXIAN_NO.length()-4)+"****";
		}else{
			BAOXIAN_NO = bAOXIAN_NO;
		}
	}

	public String getBXGS() {
		return BXGS;
	}

	public void setBXGS(String bXGS) {
		BXGS = bXGS;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getPASS_NO() {
		return PASS_NO;
	}

	public void setPASS_NO(String pASS_NO) {
		if(pASS_NO.length()>4){
			PASS_NO = pASS_NO.substring(0, pASS_NO.length()-4)+"****";
		}else{
			PASS_NO = pASS_NO;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(address.length()>11){
			this.address = address.subSequence(0, 11)+"******";
		}else{
			this.address = address;
		}
	}
	
	public String getBAOFEI() {
		return BAOFEI;
	}

	public void setBAOFEI(String bAOFEI) {
		if(bAOFEI.length()>1){
			BAOFEI = "*"+bAOFEI.substring(1, bAOFEI.length());
		}else{
			BAOFEI = bAOFEI;
		}
	}

	public String getACCOUNT_ORG() {
		return ACCOUNT_ORG;
	}

	public void setACCOUNT_ORG(String aCCOUNT_ORG) {
		if(aCCOUNT_ORG.length()>4){
			ACCOUNT_ORG = aCCOUNT_ORG.substring(0, aCCOUNT_ORG.length()-4)+"****";
		}else{
			ACCOUNT_ORG = aCCOUNT_ORG;
		}
	}

	public String getSHUHUI_TAG() {
		return SHUHUI_TAG;
	}

	public void setSHUHUI_TAG(String sHUHUI_TAG) {
		SHUHUI_TAG = sHUHUI_TAG;
	}

	public String getPOL_STAT_FLAG() {
		return POL_STAT_FLAG;
	}

	public void setPOL_STAT_FLAG(String pOL_STAT_FLAG) {
		POL_STAT_FLAG = pOL_STAT_FLAG;
	}

	public String getPOL_FINAL_DATE() {
		return POL_FINAL_DATE;
	}

	public void setPOL_FINAL_DATE(String pOL_FINAL_DATE) {
		POL_FINAL_DATE = pOL_FINAL_DATE;
	}

	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}

	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}

	public String getPSN_18() {
		return PSN_18;
	}

	public void setPSN_18(String pSN_18) {
		if(pSN_18.length()>4){
			PSN_18 = pSN_18.substring(0, pSN_18.length()-4)+"****";
		}else{
			PSN_18 = pSN_18;
		}
	}

	public String getENDDATE() {
		return ENDDATE;
	}

	public void setENDDATE(String eNDDATE) {
		ENDDATE = eNDDATE;
	}

	public String getQuery_type() {
		return query_type;
	}

	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}


	
	
	
}
