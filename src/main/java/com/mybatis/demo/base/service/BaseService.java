package com.mybatis.demo.base.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.mybatis.demo.base.web.QueryResult;




public interface BaseService<T> {

	   /**
     *  取得分页数据
     * @param firstindex
     * @param maxresult
     * @param wherejpql
     * @param queryParams
     * @param orderby
     * @return
     */
    public QueryResult<T> getScrollData(int firstindex, int maxresult,String wheresql,LinkedHashMap<String, String> orderby);
    
    
    public QueryResult<T> getScrollData(int firstindex , int maxresult);
    
    public QueryResult<T> getScrollData(int firstindex , int maxresult ,String wheresql);
    
    public QueryResult<T> getScrollData(int firstindex , int maxresult , LinkedHashMap<String, String> orderby);
    
    //直接执行更新sql
    public int executeUpdate(String sql);
    
    //直接执行查询sql
    public QueryResult<T> executeQuery(String countSql , String querySql);
   
    
	
}
