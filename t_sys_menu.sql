INSERT INTO `t_sys_menu` VALUES (2, '系统管理', 'sys_menu', 0, '', 0, '', 'icon-tasks', 1);
INSERT INTO `t_sys_menu` VALUES (3, '参数设置', 'params_menu', 2, 'gmanage.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (4, '组织管理', 'group_menu', 2, 'gmanage.do', 1, 'add_btn,edit_btn,del_btn,audit_btn', NULL, 0);
INSERT INTO `t_sys_menu` VALUES (5, '角色管理', 'role_menu', 2, 'romanage.do', 1, 'add_btn,edit_btn,del_btn,audit_btn', NULL, 0);
INSERT INTO `t_sys_menu` VALUES (6, '用户管理', 'user_menu', 2, 'umanage.do', 1, 'add_btn,edit_btn,del_btn,audit_btn', NULL, 0);
INSERT INTO `t_sys_menu` VALUES (7, '会员管理', 'auser_menu', 2, 'memberManager.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (8, '会员管理', 'member_menu', 0, '', 0, NULL, 'icon-heart-empty', 1);
INSERT INTO `t_sys_menu` VALUES (9, '会员列表', 'memberlist_menu', 8, 'memberManager.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (10, '商品管理', 'goods_menu', 0, NULL, 0, NULL, 'icon-keyboard', 1);
INSERT INTO `t_sys_menu` VALUES (11, '商品列表', 'goodslist_menu', 10, '/goods/list.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (12, '商品审核', 'goodsaudit_menu', 10, '/goods/toAuditList.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (13, '商品报表', 'goodsreport_menu', 10, '/goods/toReportList.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (16, '订单管理', 'order_menu', 0, NULL, 0, NULL, 'icon-exchange', 1);
INSERT INTO `t_sys_menu` VALUES (17, '订单审核', 'order_audit', 16, '/order/oaudit.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (18, '订单报表', 'order_export', 16, '/order/export.do', 1, NULL, NULL, 0);
INSERT INTO `t_sys_menu` VALUES (19, '会员卡管理', 'vipcard_menu', 8, 'vipcardManager.do', 1, NULL, NULL, 0);
