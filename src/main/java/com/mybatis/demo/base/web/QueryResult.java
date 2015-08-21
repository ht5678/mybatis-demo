package com.mybatis.demo.base.web;

import java.util.List;

/**
 * 
 * 类QueryResult.java的实现描述：查询结果
 * @author yuezhihua 2014年10月21日 上午10:15:34
 */
public class QueryResult<T> {

    private List<T> resultlist ;
    
    private long totalrecord;
    
    public QueryResult(){}
    
    public QueryResult(List<T> resultlist, long totalrecord) {
        super();
        this.resultlist = resultlist;
        this.totalrecord = totalrecord;
    }
    
    
    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
    }
    
    
}       
