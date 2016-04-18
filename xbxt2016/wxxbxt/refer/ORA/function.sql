CREATE OR REPLACE FUNCTION F_PARAMS(  in_type varchar2 ,in_val varchar2) RETURN varchar2 IS
out_val varchar2(4000);

BEGIN
 
   select text into out_val from t_sys_param where value=in_val and type=UPPER(in_type);
   RETURN out_val;
   EXCEPTION
     WHEN NO_DATA_FOUND THEN
       RETURN in_val||'?????';
     WHEN OTHERS THEN
       RAISE;
END F_PARAMS;
/