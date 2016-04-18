package com.tongdatech.sys.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xl 
 * @version   2014-2-28 下午12:55:31
 * 标注对象中需要记日志的字段  <br>
 * 主要指数据库日志<br>
 * 字段必须和数据库列对应
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD)
public @interface LogField {

}
