package com.mybatis.demo.base.type;


/**
 * 
 * 类BopsUnknownTypeHandler.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2014年11月14日 下午2:58:58
 */
public class BopsUnknownTypeHandler implements BopsTypeHandler<Object>{

    @Override
    public Object getNullableResult(Object value) {
        return value;
    }

}
