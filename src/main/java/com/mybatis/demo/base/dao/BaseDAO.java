package com.mybatis.demo.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * 
 * 类BaseDAO.java的实现描述：通用dao层
 * @author yuezhihua 2014年10月21日 上午10:22:27
 */
public interface BaseDAO {
    
    /**
     * 查询所有的记录
     * @param sql
     * @return
     */
    @Select("${sql}")
    public long getTotalRecord(@Param("sql")String sql);
    
    
    /**
     *  执行更新
     * @param firstindex
     * @param maxresult
     * @param wherejpql
     * @param queryParams
     * @param orderby
     * @return
     */
   @Update("${sql}")
   public int executeUpdate(@Param("sql")String sql);
   
   
   
   /**
    *  执行更新
    * @param firstindex
    * @param maxresult
    * @param wherejpql
    * @param queryParams
    * @param orderby
    * @return
    */
  @Select("${sql}")
  public List<Map<String, Object>> executeQuery(@Param("sql")String sql);
  
}
