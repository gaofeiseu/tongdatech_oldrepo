package com.tongdatech.sys.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author xl 
 * @version   2014-2-28 下午12:55:31
 * 标注继承 com.tongdatech.sys.base.PaginationAction 方法是否需要分页
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Pageable {
}
