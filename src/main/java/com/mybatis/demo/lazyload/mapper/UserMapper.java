package com.mybatis.demo.lazyload.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.mybatis.demo.common.po.RolePO;
import com.mybatis.demo.common.po.UserPO;

/**
 * 
 * 类UserMapper.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2015年7月9日 上午11:18:30
 */
public interface UserMapper {
	
	
	/**
	 * 根据用户id查询用户角色
	 * @param userId
	 * @return
	 */
	@Select("select * from role_main a INNER JOIN user_role b ON a.id = b.role_id WHERE b.user_id=#{userId}")
	public List<RolePO> getRolesByUserId(@Param("userId")Integer userId);
	
	
	/**
	 * 根据用户id查询用户角色名
	 * @param userId
	 * @return
	 */
	@Select("select a.role_name from role_main a INNER JOIN user_role b ON a.id = b.role_id WHERE b.user_id=#{userId}")
	public Set<String> getRoleNamesByUserId(@Param("userId")Integer userId);
	
	
	/**
	 * 根据userid查询用户的所有权限
	 * @param userId
	 * @return
	 */
	@Select("SELECT a.permission_name FROM permission_main a INNER JOIN role_permission b ON a.id=b.permission_id WHERE b.role_id IN (SELECT d.role_id from user_main c INNER JOIN user_role d ON c.id = d.user_id WHERE c.id=#{userId})")
	public Set<String> getPermissionsByUserId(@Param("userId")Integer userId);
	
	/**
	 * 通过用户名查询用户信息
	 * @param username
	 * @return
	 */
	@Select("select * from user_main where username=#{username}")
	@Results({
	      @Result(property = "roleNames", column = "id", many = @Many(fetchType=FetchType.LAZY,select = "getRoleNamesByUserId")),
	      @Result(property = "permissionNames", column = "id", many = @Many(fetchType=FetchType.LAZY,select = "getPermissionsByUserId"))
	  })
	public UserPO getUserByUsername(@Param("username")String username);
	
	
	@Select("select username from user_main")
	public List<String> getRoleMain();

}
