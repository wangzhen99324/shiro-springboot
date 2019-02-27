CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮id',
  `parent_id` bigint(20) NOT NULL COMMENT '上级菜单id',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `path` varchar(255) DEFAULT NULL COMMENT '对应路由path',
  `component` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
  `perms` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `order_num` double(20,0) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '/system', 'pageview', NULL, 'appstore-o', '0', 1, '2017-12-27 16:39:07', '2019-01-05 11:13:14');
INSERT INTO `sys_menu` VALUES (2, 0, '系统监控', '/monitor', 'pageview', NULL, 'dashboard', '0', 2, '2017-12-27 16:45:51', '2019-01-23 06:27:12');
INSERT INTO `sys_menu` VALUES (3, 1, '用户管理', '/system/user', 'system/user/user', 'user:view', '', '0', 1, '2017-12-27 16:47:13', '2019-01-22 06:45:55');
INSERT INTO `sys_menu` VALUES (4, 1, '角色管理', '/system/role', 'system/role/role', 'role:view', '', '0', 2, '2017-12-27 16:48:09', '2018-04-25 09:01:12');
INSERT INTO `sys_menu` VALUES (5, 1, '菜单管理', '/system/menu', 'system/menu/menu', 'menu:view', '', '0', 3, '2017-12-27 16:48:57', '2018-04-25 09:01:30');
INSERT INTO `sys_menu` VALUES (6, 1, '部门管理', '/system/dept', 'system/dept/dept', 'dept:view', '', '0', 4, '2017-12-27 16:57:33', '2018-04-25 09:01:40');
INSERT INTO `sys_menu` VALUES (8, 2, '在线用户', '/monitor/online', 'monitor/online', 'user:online', '', '0', 1, '2017-12-27 16:59:33', '2018-04-25 09:02:04');
INSERT INTO `sys_menu` VALUES (10, 2, '系统日志', '/monitor/systemlog', 'monitor/systemlog', 'log:view', '', '0', 2, '2017-12-27 17:00:50', '2018-04-25 09:02:18');
INSERT INTO `sys_menu` VALUES (11, 3, '新增用户', '', '', 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `sys_menu` VALUES (12, 3, '修改用户', '', '', 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `sys_menu` VALUES (13, 3, '删除用户', '', '', 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `sys_menu` VALUES (14, 4, '新增角色', '', '', 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `sys_menu` VALUES (15, 4, '修改角色', '', '', 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `sys_menu` VALUES (16, 4, '删除角色', '', '', 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `sys_menu` VALUES (17, 5, '新增菜单', '', '', 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `sys_menu` VALUES (18, 5, '修改菜单', '', '', 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `sys_menu` VALUES (19, 5, '删除菜单', '', '', 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `sys_menu` VALUES (20, 6, '新增部门', '', '', 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `sys_menu` VALUES (21, 6, '修改部门', '', '', 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `sys_menu` VALUES (22, 6, '删除部门', '', '', 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `sys_menu` VALUES (23, 8, '踢出用户', '', '', 'user:kickout', NULL, '1', NULL, '2017-12-27 17:11:13', NULL);
INSERT INTO `sys_menu` VALUES (24, 10, '删除日志', '', '', 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', NULL);
INSERT INTO `sys_menu` VALUES (58, 0, '网络资源', '/web', 'pageview', NULL, 'compass', '0', 4, '2018-01-12 15:28:48', '2018-01-22 19:49:26');
INSERT INTO `sys_menu` VALUES (59, 58, '天气查询', '/web/weather', 'web/weather', 'weather:view', '', '0', 1, '2018-01-12 15:40:02', '2019-01-22 05:43:19');
INSERT INTO `sys_menu` VALUES (61, 58, '每日一文', '/web/dailyarticle', 'web/dailyarticle', 'article:view', '', '0', 2, '2018-01-15 17:17:14', '2019-01-22 05:43:27');
INSERT INTO `sys_menu` VALUES (64, 1, '字典管理', '/system/dict', 'system/dict/dict', 'dict:view', '', '0', 5, '2018-01-18 10:38:25', '2018-04-25 09:01:50');
INSERT INTO `sys_menu` VALUES (65, 64, '新增字典', '', '', 'dict:add', NULL, '1', NULL, '2018-01-18 19:10:08', NULL);
INSERT INTO `sys_menu` VALUES (66, 64, '修改字典', '', '', 'dict:update', NULL, '1', NULL, '2018-01-18 19:10:27', NULL);
INSERT INTO `sys_menu` VALUES (67, 64, '删除字典', '', '', 'dict:delete', NULL, '1', NULL, '2018-01-18 19:10:47', NULL);
INSERT INTO `sys_menu` VALUES (81, 58, '影视资讯', '/web/movie', 'emptypageview', NULL, NULL, '0', 3, '2018-01-22 14:12:59', '2019-01-22 05:43:35');
INSERT INTO `sys_menu` VALUES (82, 81, '正在热映', '/web/movie/hot', 'web/moviehot', 'movie:hot', '', '0', 1, '2018-01-22 14:13:47', '2019-01-22 05:43:52');
INSERT INTO `sys_menu` VALUES (83, 81, '即将上映', '/web/movie/coming', 'web/moviecoming', 'movie:coming', '', '0', 2, '2018-01-22 14:14:36', '2019-01-22 05:43:58');
INSERT INTO `sys_menu` VALUES (101, 0, '任务调度', '/job', 'pageview', NULL, 'clock-circle-o', '0', 3, '2018-01-11 15:52:57', NULL);
INSERT INTO `sys_menu` VALUES (102, 101, '定时任务', '/job/job', 'quartz/job/job', 'job:view', '', '0', 1, '2018-02-24 15:53:53', '2019-01-22 05:42:50');
INSERT INTO `sys_menu` VALUES (103, 102, '新增任务', '', '', 'job:add', NULL, '1', NULL, '2018-02-24 15:55:10', NULL);
INSERT INTO `sys_menu` VALUES (104, 102, '修改任务', '', '', 'job:update', NULL, '1', NULL, '2018-02-24 15:55:53', NULL);
INSERT INTO `sys_menu` VALUES (105, 102, '删除任务', '', '', 'job:delete', NULL, '1', NULL, '2018-02-24 15:56:18', NULL);
INSERT INTO `sys_menu` VALUES (106, 102, '暂停任务', '', '', 'job:pause', NULL, '1', NULL, '2018-02-24 15:57:08', NULL);
INSERT INTO `sys_menu` VALUES (107, 102, '恢复任务', '', '', 'job:resume', NULL, '1', NULL, '2018-02-24 15:58:21', NULL);
INSERT INTO `sys_menu` VALUES (108, 102, '立即执行任务', '', '', 'job:run', NULL, '1', NULL, '2018-02-24 15:59:45', NULL);
INSERT INTO `sys_menu` VALUES (109, 101, '调度日志', '/job/log', 'quartz/log/joblog', 'joblog:view', '', '0', 2, '2018-02-24 16:00:45', '2019-01-22 05:42:59');
INSERT INTO `sys_menu` VALUES (110, 109, '删除日志', '', '', 'joblog:delete', NULL, '1', NULL, '2018-02-24 16:01:21', NULL);
INSERT INTO `sys_menu` VALUES (113, 2, 'redis监控', '/monitor/redis/info', 'monitor/redisinfo', 'redis:view', '', '0', 3, '2018-06-28 14:29:42', NULL);
INSERT INTO `sys_menu` VALUES (121, 2, '请求追踪', '/monitor/httptrace', 'monitor/httptrace', NULL, NULL, '0', 4, '2019-01-18 02:30:29', NULL);
INSERT INTO `sys_menu` VALUES (122, 2, '系统信息', '/monitor/system', 'emptypageview', NULL, NULL, '0', 5, '2019-01-18 02:31:48', '2019-01-18 02:39:46');
INSERT INTO `sys_menu` VALUES (123, 122, 'tomcat信息', '/monitor/system/tomcatinfo', 'monitor/tomcatinfo', NULL, NULL, '0', 2, '2019-01-18 02:32:53', '2019-01-18 02:46:57');
INSERT INTO `sys_menu` VALUES (124, 122, 'jvm信息', '/monitor/system/jvminfo', 'monitor/jvminfo', NULL, NULL, '0', 1, '2019-01-18 02:33:30', '2019-01-18 02:46:51');
INSERT INTO `sys_menu` VALUES (127, 122, '服务器信息', '/monitor/system/info', 'monitor/systeminfo', NULL, NULL, '0', 3, '2019-01-21 07:53:43', '2019-01-21 07:57:00');
INSERT INTO `sys_menu` VALUES (128, 0, '其他模块', '/others', 'pageview', NULL, 'coffee', '0', 5, '2019-01-22 06:49:59', '2019-01-22 06:50:13');
INSERT INTO `sys_menu` VALUES (129, 128, '导入导出', '/others/excel', 'others/excel', NULL, NULL, '0', 1, '2019-01-22 06:51:36', '2019-01-22 07:06:45');
INSERT INTO `sys_menu` VALUES (130, 3, '导出excel', NULL, NULL, 'user:export', NULL, '1', NULL, '2019-01-23 06:35:16', NULL);
INSERT INTO `sys_menu` VALUES (131, 4, '导出excel', NULL, NULL, 'role:export', NULL, '1', NULL, '2019-01-23 06:35:36', NULL);
INSERT INTO `sys_menu` VALUES (132, 5, '导出excel', NULL, NULL, 'menu:export', NULL, '1', NULL, '2019-01-23 06:36:05', NULL);
INSERT INTO `sys_menu` VALUES (133, 6, '导出excel', NULL, NULL, 'dept:export', NULL, '1', NULL, '2019-01-23 06:36:25', NULL);
INSERT INTO `sys_menu` VALUES (134, 64, '导出excel', NULL, NULL, 'dict:export', NULL, '1', NULL, '2019-01-23 06:36:43', NULL);
INSERT INTO `sys_menu` VALUES (135, 3, '密码重置', NULL, NULL, 'user:reset', NULL, '1', NULL, '2019-01-23 06:37:00', NULL);
INSERT INTO `sys_menu` VALUES (136, 10, '导出excel', NULL, NULL, 'log:export', NULL, '1', NULL, '2019-01-23 06:37:27', NULL);
INSERT INTO `sys_menu` VALUES (137, 102, '导出excel', NULL, NULL, 'job:export', NULL, '1', NULL, '2019-01-23 06:37:59', NULL);
INSERT INTO `sys_menu` VALUES (138, 109, '导出excel', NULL, NULL, 'joblog:export', NULL, '1', NULL, '2019-01-23 06:38:32', NULL);
INSERT INTO `sys_menu` VALUES (139, 128, '测试', NULL, NULL, 'test:test', NULL, '1', NULL, '2019-02-26 19:43:15', NULL);

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `alias_name` varchar(20) NOT NULL COMMENT '角色别名',
  `role_name` varchar(10) NOT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员', '管理员', '2017-12-27 16:23:11', '2019-01-23 06:45:29');
INSERT INTO `sys_role` VALUES (2, 'registusers', '注册用户', '可查看，新增，导出', '2019-01-04 14:11:28', '2019-01-23 07:37:08');
INSERT INTO `sys_role` VALUES (72, 'ordinary', '普通用户', '只可查看，好可怜哦', '2019-01-23 07:33:20', '2019-01-23 07:37:08');
INSERT INTO `sys_role` VALUES (73, 'test', '测试', '测试', '2019-02-26 19:39:05', '2019-02-26 19:39:08');

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 64);
INSERT INTO `sys_role_menu` VALUES (1, 65);
INSERT INTO `sys_role_menu` VALUES (1, 66);
INSERT INTO `sys_role_menu` VALUES (1, 67);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 113);
INSERT INTO `sys_role_menu` VALUES (1, 121);
INSERT INTO `sys_role_menu` VALUES (1, 122);
INSERT INTO `sys_role_menu` VALUES (1, 124);
INSERT INTO `sys_role_menu` VALUES (1, 123);
INSERT INTO `sys_role_menu` VALUES (1, 125);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 107);
INSERT INTO `sys_role_menu` VALUES (1, 108);
INSERT INTO `sys_role_menu` VALUES (1, 109);
INSERT INTO `sys_role_menu` VALUES (1, 110);
INSERT INTO `sys_role_menu` VALUES (1, 58);
INSERT INTO `sys_role_menu` VALUES (1, 59);
INSERT INTO `sys_role_menu` VALUES (1, 61);
INSERT INTO `sys_role_menu` VALUES (1, 81);
INSERT INTO `sys_role_menu` VALUES (1, 82);
INSERT INTO `sys_role_menu` VALUES (1, 83);
INSERT INTO `sys_role_menu` VALUES (1, 127);
INSERT INTO `sys_role_menu` VALUES (1, 128);
INSERT INTO `sys_role_menu` VALUES (1, 129);
INSERT INTO `sys_role_menu` VALUES (1, 130);
INSERT INTO `sys_role_menu` VALUES (1, 135);
INSERT INTO `sys_role_menu` VALUES (1, 131);
INSERT INTO `sys_role_menu` VALUES (1, 132);
INSERT INTO `sys_role_menu` VALUES (1, 133);
INSERT INTO `sys_role_menu` VALUES (1, 134);
INSERT INTO `sys_role_menu` VALUES (1, 136);
INSERT INTO `sys_role_menu` VALUES (1, 137);
INSERT INTO `sys_role_menu` VALUES (1, 138);
INSERT INTO `sys_role_menu` VALUES (3, 139);

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `sys_user` (
  `user_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` char(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近访问时间',
  `ssex` char(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `avatar` varchar(100) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'mrbird', '94f860c4bbfeb2f49c84e321fdda4b07', 1, 'mrbird123@hotmail.com', '13455533233', '1', '2017-12-27 15:47:19', '2019-01-17 02:34:19', '2019-01-28 01:53:58', '2', '我是帅比作者。', 'ubnksifajtxigxoklcin.png');
INSERT INTO `sys_user` VALUES (2, 'angzk', 'e10adc3949ba59abbe56e057f20f883e', 1, 'mrbird123@hotmail.com', '13455533233', '1', '2019-02-26 19:25:29', '2019-02-26 19:24:58', '2019-01-28 01:53:58', '2', '我是测试', 'ubnksifajtxigxoklcin.png');
INSERT INTO `sys_user` VALUES (3, 'angzk1', 'e10adc3949ba59abbe56e057f20f883e', 1, 'mrbird123@hotmail.com', '13455533233', '1', '2019-02-26 19:25:29', '2019-02-26 19:24:58', '2019-01-28 01:53:58', '2', '我是测试', 'ubnksifajtxigxoklcin.png');

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联信息表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (2, 72);
INSERT INTO `sys_user_role` VALUES (3, 73);

SET FOREIGN_KEY_CHECKS = 1;
