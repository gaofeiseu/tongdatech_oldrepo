-----机构层级
create  or replace view  t_sys_brch_path
as
SELECT     CONNECT_BY_ROOT (brch_no) root_no,
           CONNECT_BY_ROOT (brch_name) root_name,
           CONNECT_BY_ROOT (brch_mode) root_mode,
           CONNECT_BY_ROOT (brch_up) root_up,
           brch_no leaf_no,
           brch_name leaf_name, brch_mode leaf_mode,brch_up leaf_up,
           SYS_CONNECT_BY_PATH (brch_name, '>') brch_PATH, LEVEL lvl
      FROM t_sys_brch
START WITH brch_no in (select brch_no from t_sys_brch)
CONNECT BY PRIOR brch_no = brch_up;



----地区层级
create  view t_sys_area_path
as
SELECT     CONNECT_BY_ROOT (area_no) root_no,
           CONNECT_BY_ROOT (area_name) root_name,
           CONNECT_BY_ROOT (area_lvl) root_lvl, area_no leaf_no,
           area_name leaf_name, area_lvl leaf_lvl,
           SYS_CONNECT_BY_PATH (area_name, '>') area_PATH, LEVEL lvl
      FROM t_sys_area
START WITH area_no in(select area_no from t_sys_area)
CONNECT BY PRIOR area_no = area_parent;
