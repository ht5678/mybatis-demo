package com.mybatis.demo.common.po;

import java.util.Date;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mybatis.demo.common.enumitem.BooleanEnum;
import com.mybatis.demo.common.enumitem.SexEnum;

/**
 * 
 * 类UserPO.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2015年7月2日 下午4:41:57
 */
@Table(name="user_main")
public class UserPO {
	
	//主键
	@Id
    private Integer id;
    //登录名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //头像
    private String profile;
    //是否被冻结
    private BooleanEnum locked;
    //性别
    private SexEnum sex;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //用户的角色名
    @Transient
    private Set<String> roleNames;
    //用户的权限
    @Transient
    private Set<String> permissionNames;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public SexEnum getSex() {
		return sex;
	}
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public BooleanEnum getLocked() {
		return locked;
	}
	public void setLocked(BooleanEnum locked) {
		this.locked = locked;
	}
	public Set<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}
	public Set<String> getPermissionNames() {
		return permissionNames;
	}
	public void setPermissionNames(Set<String> permissionNames) {
		this.permissionNames = permissionNames;
	}

    
}
