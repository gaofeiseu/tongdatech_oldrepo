package com.tongdatech.sys.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;





/**
 * ���乤����       <br>
 * @author xl 
 * @version    2014-4-11 ����11:27:11
 */
public class ReflectionUtil {
	  
    /**
     * ���ϻ����������ֶ�   
     * @param clazz
     * @param fieldName
     * @return Field
     */
    public static Field getDeclaredField(final Class<?> clazz,   
            final String fieldName) {   
/**JUNIT?**/    	
//        Assert.notNull(clazz, "clazz����Ϊ��");   
//        Assert.hasText(fieldName, "fieldName");   
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass   
                .getSuperclass()) {   
            try {   
                return superClass.getDeclaredField(fieldName);   
            } catch (NoSuchFieldException e) {   
                // Field���ڵ�ǰ�ඨ��,��������ת��   
            }   
        }   
        return null;   
    }   
  

    /**
     * ���ö����set����   
     * @param instance
     * @param field
     * @param fieldValue
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void invokeSetMethod(Object instance, Field field, Object fieldValue)   
            throws SecurityException, NoSuchMethodException,   
            IllegalArgumentException, IllegalAccessException,   
            InvocationTargetException {   
        String fieldName = field.getName();   
  
        String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase()   
                + fieldName.substring(1);   
  
        Method setMethod = instance.getClass().getMethod(setMethodName,   
                field.getType());   
        setMethod.invoke(instance, fieldValue);   
    }   
    
    /**
     * ���ö����get����   
     * @param instance
     * @param field
     * @return Object
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokeGetMethod(Object instance, Field field)   
            throws SecurityException, NoSuchMethodException,   
            IllegalArgumentException, IllegalAccessException,   
            InvocationTargetException {   
        String fieldName = field.getName();   
  
        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()   
                + fieldName.substring(1);   

        Method getMethod = instance.getClass().getMethod(getMethodName);   
        return getMethod.invoke(instance);   
    }   
  
 
    /**
     * ���ָ��������ֵ   
     * @param instance
     * @param fieldName
     * @return Object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object instance, String fieldName)   
            throws IllegalArgumentException, IllegalAccessException {   
  
        Field field = getDeclaredField(instance.getClass(), fieldName);   
        return getFieldValue(instance, field);   
    }   
  

    /**
     * ���ָ��������ֵ   
     * @param instance
     * @param field
     * @return Object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object instance, Field field)   
            throws IllegalArgumentException, IllegalAccessException {   
  
        // ����ֵΪtrue�����÷��ʿ��Ƽ��   
        field.setAccessible(true);   
        return field.get(instance);   
    }   

    /**
     * Ϊ������������ֵ   
     * @param instance
     * @param fieldName
     * @param fieldValue
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void setFieldForObject(Object instance, String fieldName,   
            Object fieldValue) throws InstantiationException,   
            IllegalAccessException, SecurityException,   
            IllegalArgumentException, NoSuchMethodException,   
            InvocationTargetException {   
  
        int index = fieldName.indexOf(".");   
  
        if (index != -1) { // Ҫ���õ���һ��ʵ�����   
  
            String entityName = fieldName.substring(0, index);   
            String subString = fieldName.substring(index + 1);   
  
            Field field = getDeclaredField(instance.getClass(), entityName);   
  
            if (field != null) {   
                Object entity = getFieldValue(instance, field);   
                if (entity == null) { // �����ڵĻ��ʹ���ʵ�����   
                    entity = field.getType().newInstance();   
                }   
                // �ݹ�   
                setFieldForObject(entity, subString, fieldValue);   
                // ��ʵ��������õ���������   
                invokeSetMethod(instance, field, entity);   
            }   
  
        } else { // Ҫ���õ���һ�������Ͷ���   
            Field field = ReflectionUtil.getDeclaredField(instance.getClass(),   
                    fieldName);   
  
            if (field != null) {   
                Object value = ConvertUtils.convert(fieldValue.toString(), field.getType()); // ��ֵת��Ϊ��Ӧ������   
                invokeSetMethod(instance, field, value);   
            }   
        }   
  
    }
    /**
    * �ж϶���oʵ�ֵ����нӿ����Ƿ���szInterface<br>
    * �ݹ�ʵ�ֶ�̳����жϽӿڵĹ��ܣ�e.g. <br>
    * package test; <br>
    *  
    * public interface ITest extends Serializable <br>
    * public class Test1 implements ITest <br>
    * public class Test2 extends Test1 <br>
    * public class Test3 extends Test2  <br>
    *  
    * isInterface(Test3.class, "java.io.Serializable") = true <br>
    * isInterface(Test3.class, "test.ITest") = true <br>
    * @param c 
    * @param szInterface 
    * @param isRecursive  �Ƿ�ݹ���
    * @return boolean
    */ 

    public static boolean isInterface(Class<?> c, String szInterface,boolean isRecursive ){
    	Class<?>[] face = c.getInterfaces(); 
        for (int i = 0, j = face.length; i < j; i++)  
        { 
                if(face[i].getName().equals(szInterface)) 
                { 
                        return true; 
                } 
                else if(isRecursive)
                {  
                        Class<?>[] face1 = face[i].getInterfaces(); 
                        for(int x = 0; x < face1.length; x++) 
                        { 
                                if(face1[x].getName().equals(szInterface)) 
                                { 
                                        return true; 
                                } 
                                else if(isInterface(face1[x], szInterface,true)) 
                                { 
                                        return true; 
                                } 
                        } 
                } else{
                	return false;
                }
        } 
        if (null != c.getSuperclass()) 
        { 
                return isInterface(c.getSuperclass(), szInterface,true); 
        } 
        return false; 

    }



	/**
	 * ��ȡ��ע�ֶ�
	 * @param instance
	 * @param AnnotationName
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, Object> getAnnotationField(
			Object instance, String AnnotationName) throws Exception {


		Map<String, Object> mp = new HashMap<String, Object>();
		Class<?> classType = instance.getClass();
		Field[] fields = classType.getDeclaredFields();
		Annotation[] annotations;
		for (Field field : fields) {

			if (field.isSynthetic())
				continue;// ���ֶ��Ǹ����ֶ�
			annotations = field.getAnnotations();

			for (Annotation annotation : annotations) {
				if (AnnotationName.equals(annotation.annotationType().getName())) {
					mp.put(field.getName(), getFieldValue(instance, field));
				}
			}
		}
		return mp;
	}
	
	/**
	 * �����Ƿ���AnnotationName��ע
	 * @param AnnotationName
	 * @param theMethod Method����
	 * @return boolean
	 */
	public static boolean isAnnotation(String AnnotationName,Method theMethod){
		Annotation[] annotations= theMethod.getAnnotations();
		for (Annotation annotation : annotations) {
			if (AnnotationName.equals(annotation.annotationType().getName())) {
               return true;
			}
		}
		return false;
		
	}
	/**
	 * �����Ƿ���AnnotationName��ע
	 * @param clazz
	 * @param AnnotationName
	 * @param theMethodName String����
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isAnnotation(Class<? extends Object> clazz,String AnnotationName,String theMethodName) throws Exception{
		Method theMethod =clazz.getMethod(theMethodName); 
        return isAnnotation(AnnotationName,theMethod);
		
	}
	
	private static  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * Ĭ�϶���תString ����
	 * @param obj
	 * @return String
	 */
	public static String parseParams(Object obj){
		if(obj instanceof String)return (String)obj;
		if(obj instanceof Integer)return String.valueOf(obj);
		if(obj instanceof Double)return String.valueOf(obj);
		if(obj instanceof Float)return String.valueOf(obj);
		if(obj instanceof Boolean)return String.valueOf(obj);
		if(obj instanceof Character)return String.valueOf(obj);
		if(obj instanceof Long)return String.valueOf(obj);
		if(obj instanceof Date){
			Date date = (Date)obj;
			return df.format(date);
		}
		return String.valueOf(obj);
		
	}
}
