package com.tongdatech.sys.annotation;
import java.lang.annotation.*;
/**
 * @author xl 
 * @version   2014-2-28 下午12:55:31
 * 标注采用非EasyUI分页的需要专递的参数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD)
public @interface PageParam {

}
