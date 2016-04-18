--bdm 中取数 t_sys_brch
select sn,parent_sn,dept_name,dept_no,province_no||area_no,flag order_id,dept_mode,spell_s,spell_a from t_dept where province_no=32 and summary is null;
--bdm 中取数 t_sys_area
select province_no||city_id,parent_prov||parent_id,city_name,city_class,gov_code,'1'city_flag from t_city where province_no in(32,00);