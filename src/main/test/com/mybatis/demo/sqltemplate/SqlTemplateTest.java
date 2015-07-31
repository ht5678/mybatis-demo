package com.mybatis.demo.sqltemplate;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.demo.BaseDataTest;
import com.mybatis.demo.common.po.UserPO;


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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 测试查询
     * java.lang.UnsupportedOperationException: Manual close is not allowed over a Spring managed SqlSession不要在意
     */
    @Test
    public void testSelect(){
        UserPO  result = sqlSessionTemplate.selectOne("com.mybatis.demo.lazyload.mapper.UserMapper.getUserByUsername", "zhangsan");
        System.out.println(result);
    }
    

}
