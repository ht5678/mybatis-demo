package com.mybatis.demo;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * 
 * 类BaseDataTest.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2015年7月9日 上午11:46:12
 */
public class BaseDataTest {
	
	public static final String DERBY_PROPERTIES = "config/derby.properties";
	
	public static final String HSQL_PROPERTIES = "config/hsql.properties";
	
	  /**
	   * 创建UnpooledDataSource类型的数据源
	   * @param resource
	   * @return
	   * @throws IOException
	   */
	  public static UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
	    //将配置文件加载到Properties里边
	    Properties props = Resources.getResourceAsProperties(resource);
	    
	    UnpooledDataSource ds = new UnpooledDataSource();
	    ds.setDriver(props.getProperty("driver"));
	    ds.setUrl(props.getProperty("url"));
	    ds.setUsername(props.getProperty("username"));
	    ds.setPassword(props.getProperty("password"));
	    return ds;
	  }

	  
	  /**
	   * 创建PooledDataSource，里边会维护一个数据连接池
	   * @param resource
	   * @return
	   * @throws IOException
	   */
	  public static PooledDataSource createPooledDataSource(String resource) throws IOException {
	    //将配置文件加载到Properties里边
	    Properties props = Resources.getResourceAsProperties(resource);
	    
	    PooledDataSource ds = new PooledDataSource();
	    ds.setDriver(props.getProperty("driver"));
	    ds.setUrl(props.getProperty("url"));
	    ds.setUsername(props.getProperty("username"));
	    ds.setPassword(props.getProperty("password"));
	    return ds;
	  }
	  
	  
	  /**
	   * 运行资源脚本sql
	   * @param ds
	   * @param resource
	   * @throws IOException
	   * @throws SQLException
	   */
	  public static void runScript(DataSource ds, String resource) throws IOException, SQLException {
	    Connection connection = ds.getConnection();
	    try {
	      ScriptRunner runner = new ScriptRunner(connection);
	      runner.setAutoCommit(true);
	      runner.setStopOnError(false);
	      runner.setLogWriter(null);
	      runner.setErrorLogWriter(null);
	      runScript(runner, resource);
	    } finally {
	      connection.close();
	    }
	  }

	  
	  /**
	   * ScriptRunner设置来运行资源脚本sql
	   * @param runner
	   * @param resource
	   * @throws IOException
	   * @throws SQLException
	   */
	  public static void runScript(ScriptRunner runner, String resource) throws IOException, SQLException {
	    Reader reader = Resources.getResourceAsReader(resource);
	    try {
	      runner.runScript(reader);
	    } finally {
	      reader.close();
	    }
	  }
	  

}
