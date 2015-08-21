package com.mybatis.demo.base.type;



/**
 * 
 * 类BopsTypeHandler.java的实现描述：接口扩展
 * @author yuezhihua 2014年11月14日 下午2:01:23
 */
public interface BopsTypeHandler<T> {   

    public T getNullableResult(Object value);
    
}
