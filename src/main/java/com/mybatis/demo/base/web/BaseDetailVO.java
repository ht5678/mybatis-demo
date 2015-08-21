package com.mybatis.demo.base.web;

/**
 * 
 * 类BaseDetailVO.java的实现描述：返回前台单数据通用对象
 * @author yuezhihua 2014年10月30日 下午8:25:49
 */
public class BaseDetailVO<T> {

    private T data;
    
    private String code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
