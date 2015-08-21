package com.mybatis.demo.base.type;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类BopsIntegerTypeHandler.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2014年11月14日 下午2:43:59
 */
public class BopsIntegerTypeHandler implements BopsTypeHandler<Integer>{

    @Override
    public Integer getNullableResult(Object value) {
        if(value == null || StringUtils.isEmpty(value.toString())){
            return null;
        }
        return Integer.parseInt(value.toString());
    }

}
