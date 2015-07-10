

-- ----------------------------
-- Records of permission_main
-- ----------------------------
INSERT INTO permission_main(id,permission_name,description,url,available,leaf,parent_id,orders,create_time,update_time) VALUES (1, 'BOOKVIEW', '漫画查询', null, 'TRUE', 'TRUE', '3', '1', '2015-07-02 17:40:38', '2015-07-03 10:59:01');
INSERT INTO permission_main(id,permission_name,description,url,available,leaf,parent_id,orders,create_time,update_time) VALUES (2, 'BOOKMANAGE', '漫画管理', null, 'TRUE', 'TRUE', '3', '2', '2015-07-02 17:40:49', '2015-07-03 10:59:05');
INSERT INTO permission_main(id,permission_name,description,url,available,leaf,parent_id,orders,create_time,update_time) VALUES (3, 'BOOK', '漫画', null, 'TRUE', 'FALSE', null, '1', '2015-07-03 10:58:52', '2015-07-03 10:59:20');

-- ----------------------------
-- Records of role_main
-- ----------------------------
INSERT INTO role_main(id,role_name,available,description,create_time,update_time) VALUES (1, 'ADMIN', 'TRUE', '管理员', '2015-07-02 17:39:00', '2015-07-02 17:39:00');
INSERT INTO role_main(id,role_name,available,description,create_time,update_time) VALUES (2, 'USER', 'TRUE', '用户', '2015-07-02 17:39:12', '2015-07-02 17:39:12');



-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO role_permission(role_id,permission_id) VALUES (1, 1);
INSERT INTO role_permission(role_id,permission_id) VALUES (1, 2);
INSERT INTO role_permission(role_id,permission_id) VALUES (1, 3);
INSERT INTO role_permission(role_id,permission_id) VALUES (2, 1);
INSERT INTO role_permission(role_id,permission_id) VALUES (2, 3);



-- ----------------------------
-- Records of user_main
-- ----------------------------
INSERT INTO user_main(id,username,password,email,profile,locked,sex,create_time,update_time) VALUES (1, 'zhangsan', '01d7f40760960e7bd9443513f22ab9af', 'yuezhihua@joyworks.com.cn', '5bf453b7-902b-447e-b511-ce409d79ac12', 'FALSE', 'MALE', '2015-07-02 17:34:11', '2015-07-02 20:05:22');



-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO user_role(user_id,role_id) VALUES (1, 1);




