package com.tongdatech.sys.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xl 
 * @version   2014-2-28 ����12:55:31
 * ��ע��������Ҫ����־���ֶ�  <br>
 * ��Ҫָ���ݿ���־<br>
 * �ֶα�������ݿ��ж�Ӧ
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD)
public @interface LogField {

}
