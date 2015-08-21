package com.mybatis.demo.base.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mybatis.demo.base.type.BopsBooleanTypeHandler;
import com.mybatis.demo.base.type.BopsDateTypeHandler;
import com.mybatis.demo.base.type.BopsEnumTypeHandler;
import com.mybatis.demo.base.type.BopsIntegerTypeHandler;
import com.mybatis.demo.base.type.BopsStringTypeHandler;
import com.mybatis.demo.base.type.BopsTypeHandler;
import com.mybatis.demo.base.type.BopsUnknownTypeHandler;

/**
 * 
 * 类MappedType.java的实现描述：规则匹配字段--下火线_方式
 * @author yuezhihua 2014年10月21日 下午8:41:41
 */
public class MappedTypeUtil {
    
    private static final Map<Class<?>, BopsTypeHandler<?>> ALL_TYPE_HANDLERS_MAP = new HashMap<Class<?>, BopsTypeHandler<?>>();
    
    private static final BopsTypeHandler<Object> UNKNOWN_TYPE_HANDLER = new BopsUnknownTypeHandler();
    
    static{
        ALL_TYPE_HANDLERS_MAP.put(Boolean.class, new BopsBooleanTypeHandler());
        ALL_TYPE_HANDLERS_MAP.put(boolean.class, new BopsBooleanTypeHandler());

        ALL_TYPE_HANDLERS_MAP.put(Integer.class, new BopsIntegerTypeHandler());
        ALL_TYPE_HANDLERS_MAP.put(int.class, new BopsIntegerTypeHandler());

        ALL_TYPE_HANDLERS_MAP.put(String.class, new BopsStringTypeHandler());

        ALL_TYPE_HANDLERS_MAP.put(Object.class, UNKNOWN_TYPE_HANDLER);
        
        ALL_TYPE_HANDLERS_MAP.put(Date.class, new BopsDateTypeHandler());

      }
    
    
    
    /**
     * 首字母转大写
     * @param str
     * @return
     */
    public static String toUpperCaseFirstOne(String str){
        if(Character.isUpperCase(str.charAt(0))){
            return str;
        }else{
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }
    
    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0))){
            return s;
        }else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
    
    /**
     * 获取字段方法map
     * @param clazz
     * @return
     * @throws IntrospectionException
     */
    public static Map<String, PropertyDescriptor> getPropertyMethodMap(Class<?> clazz) throws IntrospectionException{
    	BeanInfo bi = Introspector.getBeanInfo(clazz);
    	PropertyDescriptor[] pds = bi.getPropertyDescriptors();
    	Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
    	for(PropertyDescriptor pd : pds){
    		if(pd.getWriteMethod()!=null){
    			map.put(pd.getName().toUpperCase(), pd);
    		}
    	}
    	return map;
    }
    
    
    public static BopsTypeHandler<?> getTypeHandler(Class<?> propertyType){
        BopsTypeHandler<?> handler = ALL_TYPE_HANDLERS_MAP.get(propertyType);
        //枚举类型特殊处理
        if (handler == null && propertyType != null && propertyType instanceof Class && Enum.class.isAssignableFrom((Class<?>) propertyType)) {
            handler = (BopsTypeHandler<?>) new BopsEnumTypeHandler(propertyType);
          }
        return handler;
    }
    
    
    public static String getUpperPropertyName(String name) {
        name = name.replace("_", "");
        return name.toUpperCase();
      }
    
}
