package com.mybatis.demo.lazyloader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.mybatis.demo.common.po.UserPO;
import com.mybatis.demo.lazyload.mapper.UserMapper;


/**
 * 
 * 类LazyLoaderTest.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2015年7月9日 上午11:56:26
 */
public class LazyLoaderTest {
	
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
		    configuration.getTypeAliasRegistry().registerAlias(UserPO.class);
		    configuration.addMapper(UserMapper.class);
		    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	  public void testGetUserByUsername() throws Exception {
	    SqlSession session = sqlSessionFactory.openSession();
	    try {
	      UserMapper userMapper = session.getMapper(UserMapper.class);
	      UserPO userPo = userMapper.getUserByUsername("zhangsan");
	      System.out.println(userPo);
	      System.out.println(userPo.getPermissionNames());
//	      List<String> map = userMapper.getRoleMain();
//	      System.out.println(map);
	      session.close();
	    } finally {
	      session.close();
	    }
	  }

}
