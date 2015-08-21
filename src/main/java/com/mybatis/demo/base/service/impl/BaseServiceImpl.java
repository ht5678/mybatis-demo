package com.mybatis.demo.base.service.impl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.demo.base.dao.BaseDAO;
import com.mybatis.demo.base.service.BaseService;
import com.mybatis.demo.base.type.BopsTypeHandler;
import com.mybatis.demo.base.utils.Entity;
import com.mybatis.demo.base.utils.GenericsUtils;
import com.mybatis.demo.base.utils.MappedTypeUtil;
import com.mybatis.demo.base.web.QueryResult;


/**
 * 
 * 类BaseServiceImpl.java的实现描述：通用实现
 * @author yuezhihua 2014年10月21日 上午9:56:33
 */
@Service
public abstract class BaseServiceImpl<T>  implements BaseService<T> {
    /** 日志 */
    private static final Logger logger = Logger.getLogger(BaseServiceImpl.class);
    /** 泛型 */
    private Class<?> entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    
    @Autowired
    private BaseDAO baseDAO;
    
    
    
    @Override
    public QueryResult<T> getScrollData(int firstindex, int maxresult,
            String wheresql,LinkedHashMap<String, String> orderby) {
        String tableName = getTableName();
        StringBuffer countSql = new StringBuffer("select count(*) from "+tableName+" where 1=1 ");
        StringBuffer querySql = new StringBuffer("select * from "+tableName+" where 1=1 ");
        //查询条件
        if(wheresql!=null && !"".equals(wheresql)){
            countSql.append(wheresql);
            querySql.append(wheresql);
        }
        //orderby
        if(orderby!=null){
            querySql.append(buildOrderBy(orderby));
        }
        //分页
        if(firstindex!=-1 && maxresult!=-1){
            querySql.append(" limit "+firstindex+","+maxresult);
        }
        long count = baseDAO.getTotalRecord(countSql.toString());
        List<Map<String, Object>> datas = baseDAO.executeQuery(querySql.toString());
        try {
            //将数据转换成对象
            List<T> list = getDatas(datas);
            QueryResult<T> qr = new QueryResult<T>();
            qr.setTotalrecord(count);
            qr.setResultlist(list);
            return qr;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } 
        return null;
    }


    @Override
    public QueryResult<T> getScrollData(int firstindex, int maxresult) {
        return getScrollData(firstindex, maxresult, null, null);
    }

    @Override
    public QueryResult<T> getScrollData(int firstindex, int maxresult,
            String wheresql) {
        return getScrollData(firstindex, maxresult, wheresql, null);
    }

    @Override
    public QueryResult<T> getScrollData(int firstindex, int maxresult,
            LinkedHashMap<String, String> orderby) {
        return getScrollData(firstindex, maxresult, null, orderby);
    }
    
    
    @Override
    public int executeUpdate(String sql) {
        return baseDAO.executeUpdate(sql);
    }

    @Override
    public QueryResult<T> executeQuery(String countSql , String querySql) {
        List<Map<String, Object>> datas = baseDAO.executeQuery(querySql);
        long count = baseDAO.getTotalRecord(countSql);
        List<T> results = new ArrayList<T>();
        try {
            results = getDatas(datas);
            QueryResult<T> qr = new QueryResult<T>();
            qr.setResultlist(results);
            qr.setTotalrecord(count);
            return qr;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 返回实体的表名
     * @return
     */
    private String getTableName(){
        Entity entity = entityClass.getAnnotation(Entity.class);
        return entity.table();
    }
    
    
    /**
     * 组装orderby语句
     * @param orderby   
     * @return
     */
    protected static String buildOrderBy(LinkedHashMap<String, String> orderby){
    
        StringBuilder orderbyql = new StringBuilder("");
        if(orderby!=null && !orderby.isEmpty()){
            orderbyql.append(" order by ");
            for(String key : orderby.keySet()){
                orderbyql.append(key).append(" ").append(orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length()-1);
        }
        return orderbyql.toString();
    }
    
    
    /**
     * 将map转换成对象
     * @param t
     * @param datas
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<T> getDatas(List<Map<String, Object>> datas){
        List<T> retVals = new ArrayList<T>();
        try{
            Map<String, PropertyDescriptor> columnsDescriptor = MappedTypeUtil.getPropertyMethodMap(entityClass);
            for(Map<String, Object> data : datas){
                Object retVal = entityClass.getConstructor().newInstance();
                for(Entry<String, Object> entry : data.entrySet()){
                    final String property = MappedTypeUtil.getUpperPropertyName(entry.getKey());
                    PropertyDescriptor pd = columnsDescriptor.get(property);
                    if(pd==null){
                        logger.error(entityClass+"的属性"+property+"没有get/set方法");
                        continue;
                    }
                    BopsTypeHandler<?> handler = MappedTypeUtil.getTypeHandler(pd.getPropertyType());
                    pd.getWriteMethod().invoke(retVal, handler.getNullableResult(entry.getValue()));
                }
                retVals.add((T)retVal);
            }
        }catch(Exception e){
            logger.error("数据库查询结果转换对象错误:"+e.getMessage());
        }
        return retVals;
    }
    
}
