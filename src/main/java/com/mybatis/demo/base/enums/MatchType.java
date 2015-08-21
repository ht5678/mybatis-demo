package com.mybatis.demo.base.enums;


/**
 * 
 * 类MatchType.java的实现描述：查询条件
 * @author yuezhihua 2014年12月24日 下午6:09:06
 */
public enum MatchType {
	EQ,  //equal
	NE,  //not equal
	LIKE, // like 
	LT, // less than
	GT,  //greater than
	LE,  //less than or equal 
	GE,  // greater than or equal
	IN,  // in
	NOT_IN; //not in
}
