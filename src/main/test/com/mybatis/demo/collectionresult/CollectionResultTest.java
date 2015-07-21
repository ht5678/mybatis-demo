package com.mybatis.demo.collectionresult;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.demo.BaseDataTest;
import com.mybatis.demo.common.po.RolePO;
import com.mybatis.demo.common.po.UserPO;
import com.mybatis.demo.lazyload.mapper.UserMapper;

/**
 * 
 * 类CollectionResultTest.java的实现描述：测试mybatis返回的集合是否会为Null
 * @author yuezhihua 2015年7月21日 下午1:56:22
 */
public class CollectionResultTest {
    
    
private SqlSessionFactory sqlSessionFactory = null;
    
    /**
     * 初始化datasource
     */
    @Before
    public void init(){
        DataSource ds = null;
        try {
            ds = BaseDataTest.createUnpooledDataSource(BaseDataTest.DERBY_PROPERTIES);
            BaseDataTest.runScript(ds, "com/mybatis/demo/databases/lazyloader/lazyloader-schema.sql");
            BaseDataTest.runScript(ds, "com/mybatis/demo/databases/lazyloader/lazyloader-dataload.sql");
            //初始化sqlSessionFactory
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("Production", transactionFactory, ds);
            Configuration configuration = new Configuration(environment);
            configuration.setLazyLoadingEnabled(true);
            configuration.setAggressiveLazyLoading(false);
            configuration.getTypeAliasRegistry().registerAlias(UserPO.class);
            configuration.addMapper(UserMapper.class);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 测试list的查询结果为空时是否为null
     * 
     * 结果：查询结果为空的时候，集合不为null
     * 
     */
    @Test
      public void testListResult() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        try {
          UserMapper userMapper = session.getMapper(UserMapper.class);
          //查询一条不存在的userid 88 ,这个时候返回结果为空，判断roles集合是否会是null
          List<RolePO> roles = userMapper.getRolesByUserId(88);
          System.out.println(roles);
          session.close();
        } finally {
          session.close();
        }
      }

    
    
    /**
     * 判断set.addAll(list);的list如果有重复数据的情况
     */
    @Test
    public void testSet(){
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("zhangsan");
        list.add("zhangsan");
        
        set.addAll(list);
        System.out.println(list.size());
        System.out.println(set.size());
    }
    
}
