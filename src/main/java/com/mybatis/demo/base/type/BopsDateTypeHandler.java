package com.mybatis.demo.base.type;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类DateTypeHandler.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2014年11月14日 下午2:04:19
 */
public class BopsDateTypeHandler implements BopsTypeHandler<Date>{

    @Override
    public Date getNullableResult(Object value) {
        if(value ==null || StringUtils.isEmpty(value.toString())){
            return null;
        }
        return (Date)value;
    }
    
}
