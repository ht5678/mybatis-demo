package com.mybatis.demo.base.enums;

/**
 * 
 * 类DBType.java的实现描述：数据库类型
 * @author yuezhihua 2014年12月24日 下午6:09:22
 */
public enum DBType {
    MySql("mysql");
    
    private final String key;//关键字
    
    DBType(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
