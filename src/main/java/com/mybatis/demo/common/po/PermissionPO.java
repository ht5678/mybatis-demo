package com.mybatis.demo.common.po;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.mybatis.demo.common.enumitem.BooleanEnum;

/**
 * 
 * 类PermissionPO.java的实现描述：TODO 类实现描述 
 * @author yuezhihua 2015年7月2日 下午4:47:54
 */
@Table(name="permission_main")
public class PermissionPO{
	//主键
	@Id
	private Integer id;
	//权限名称
	private String permissionName;
	//描述
	private String description;
	//是否可用
	private BooleanEnum available;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BooleanEnum getAvailable() {
		return available;
	}

	public void setAvailable(BooleanEnum available) {
		this.available = available;
	}

	
	
}
