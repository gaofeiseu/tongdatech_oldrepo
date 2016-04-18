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
 * 反射工具类       <br>
 * @author xl 
 * @version    2014-4-11 上午11:27:11
 */
public class ReflectionUtil {
	  
    /**
     * 向上获得类的声明字段   
     * @param clazz
     * @param fieldName
     * @return Field
     */
    public static Field getDeclaredField(final Class<?> clazz,   
            final String fieldName) {   
/**JUNIT?**/    	
//        Assert.notNull(clazz, "clazz不能为空");   
//        Assert.hasText(fieldName, "fieldName");   
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass   
                .getSuperclass()) {   
            try {   
                return superClass.getDeclaredField(fieldName);   
            } catch (NoSuchFieldException e) {   
                // Field不在当前类定义,继续向上转型   
            }   
        }   
        return null;   
    }   
  

    /**
     * 调用对象的set方法   
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
     * 调用对象的get方法   
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
     * 获得指定变量的值   
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
     * 获得指定变量的值   
     * @param instance
     * @param field
     * @return Object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object instance, Field field)   
            throws IllegalArgumentException, IllegalAccessException {   
  
        // 参数值为true，禁用访问控制检查   
        field.setAccessible(true);   
        return field.get(instance);   
    }   

    /**
     * 为对象设置属性值   
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
  
        if (index != -1) { // 要设置的是一个实体对象   
  
            String entityName = fieldName.substring(0, index);   
            String subString = fieldName.substring(index + 1);   
  
            Field field = getDeclaredField(instance.getClass(), entityName);   
  
            if (field != null) {   
                Object entity = getFieldValue(instance, field);   
                if (entity == null) { // 不存在的话就创建实体对象   
                    entity = field.getType().newInstance();   
                }   
                // 递归   
                setFieldForObject(entity, subString, fieldValue);   
                // 把实体对象设置到父对象中   
                invokeSetMethod(instance, field, entity);   
            }   
  
        } else { // 要设置的是一个简单类型对象   
            Field field = ReflectionUtil.getDeclaredField(instance.getClass(),   
                    fieldName);   
  
            if (field != null) {   
                Object value = ConvertUtils.convert(fieldValue.toString(), field.getType()); // 把值转换为相应的类型   
                invokeSetMethod(instance, field, value);   
            }   
        }   
  
    }
    /**
    * 判断对象o实现的所有接口中是否有szInterface<br>
    * 递归实现多继承中判断接口的功能，e.g. <br>
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
    * @param isRecursive  是否递归检测
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
	 * 获取标注字段
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
				continue;// 此字段是复合字段
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
	 * 方法是否有AnnotationName标注
	 * @param AnnotationName
	 * @param theMethod Method对象
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
	 * 方法是否有AnnotationName标注
	 * @param clazz
	 * @param AnnotationName
	 * @param theMethodName String对象
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isAnnotation(Class<? extends Object> clazz,String AnnotationName,String theMethodName) throws Exception{
		Method theMethod =clazz.getMethod(theMethodName); 
        return isAnnotation(AnnotationName,theMethod);
		
	}
	
	private static  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * 默认对象转String 方法
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
