package com.mybatis.demo.base.type;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类BopsStringTypeHandler.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2014年11月14日 下午2:42:10
 */
public class BopsStringTypeHandler implements BopsTypeHandler<String> {

    @Override
    public String getNullableResult(Object value) {
        if(value == null || StringUtils.isEmpty(value.toString())){
            return "";
        }
        return value.toString();
    }

}
