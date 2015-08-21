package com.mybatis.demo.base.type;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类BopsEnumTypeHandler.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2014年11月14日 下午2:45:14
 */
public class BopsEnumTypeHandler<E extends Enum<E>> implements BopsTypeHandler<E>{
    
    private Class<E> type;

    public BopsEnumTypeHandler(Class<E> type) {
      if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
      this.type = type;
    }

    @Override
    public E getNullableResult(Object value) {
        if(value == null || StringUtils.isEmpty(value.toString())){
            return null;
        }
        return Enum.valueOf(type, value.toString());
    }

}
