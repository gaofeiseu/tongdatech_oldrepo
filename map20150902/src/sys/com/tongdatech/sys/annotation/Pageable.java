package com.tongdatech.sys.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author xl 
 * @version   2014-2-28 ����12:55:31
 * ��ע�̳� com.tongdatech.sys.base.PaginationAction �����Ƿ���Ҫ��ҳ
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Pageable {
}
