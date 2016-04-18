
package com.tongdatech.map.MobileService;

public interface MobileService {
	public String map_phone_checkuser(String params,String params2);
	public String map_phone_submitdata(String params,String params2,String params3,String params4,String params5);
	public String wap_login(String param1,String param2);
	//mobile check in main function
	public String wap_check_in(String login_name,String login_id,String check_in_lat,String check_in_lng,String visit_type,String visit_custom_name,String visit_content,String visit_note,String visit_class,String img_db_sn,String if_wap,String custom_type);
	public String wap_get_history(String login_name,String login_id,String history_time_st,String history_time_ed,String history_now_num,String history_increace_num);
	public String wap_get_history_total_num(String login_name,String login_id,String history_time_st,String history_time_ed);
	public String wap_img_keepindb(String old_filename,String now_filepath,String filesize,String filetype);
}
