package com.mybatis.demo.base.web;

import java.util.List;


/**
 * 
 * 类BaseDatasVO.java的实现描述：返回前台list通用对象
 * @author yuezhihua 2014年10月30日 下午8:25:11
 */
public class BaseDatasVO<T> {

    private List<T> datas;
    
    private String code;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
