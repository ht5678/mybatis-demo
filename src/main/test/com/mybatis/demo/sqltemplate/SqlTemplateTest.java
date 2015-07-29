package com.mybatis.demo.sqltemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.demo.BaseDataTest;


/**
 * 
 * 类SqlTemplateTest.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2015年7月29日 下午2:07:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:spring/demo-locator.xml"
})
public class SqlTemplateTest {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
//    private SqlSessionFactory sqlSessionFactory = null;
    
    
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
//            TransactionFactory transactionFactory = new JdbcTransactionFactory();
//            Environment environment = new Environment("Production", transactionFactory, ds);
//            Configuration configuration = new Configuration(environment);
//            configuration.setLazyLoadingEnabled(true);
//            configuration.setAggressiveLazyLoading(false);
//          configuration.setLazyLoadTriggerMethods(new HashSet<String>());
//            configuration.getTypeAliasRegistry().registerAlias(UserPO.class);
//            configuration.addMapper(UserMapper.class);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 测试查询
     */
    @Test
    public void testSelect(){
        List<?> result = sqlSessionTemplate.selectList("com.mybatis.demo.lazyload.mapper.UserMapper.getRolesByUserId", 1);
        System.out.println(result);
    }
    

}
