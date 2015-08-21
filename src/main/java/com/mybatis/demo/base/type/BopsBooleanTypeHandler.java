package com.mybatis.demo.base.type;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类BopsBooleanTypeHandler.java的实现描述：mybatis-boolean类型扩展
 * @author yuezhihua 2014年11月14日 下午1:58:34
 */
public class BopsBooleanTypeHandler implements BopsTypeHandler<Boolean>{

    @Override
    public Boolean getNullableResult(Object value) {
        if(value == null || StringUtils.isEmpty(value.toString())){
            return Boolean.FALSE;
        }
        return Boolean.valueOf(value.toString());
    }

}
