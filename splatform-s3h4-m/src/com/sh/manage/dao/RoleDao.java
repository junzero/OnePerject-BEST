/**
 * 
 */
package com.sh.manage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sh.manage.constants.SessionConstants;
import com.sh.manage.entity.AppUser;
import com.sh.manage.entity.SysGroupRole;
import com.sh.manage.entity.SysMenu;
import com.sh.manage.entity.SysRole;
import com.sh.manage.entity.SysRoleMenu;
import com.sh.manage.exception.SPlatformDaoException;
import com.sh.manage.module.page.Page;
import com.sh.manage.utils.SQLPagingUtils;

/**
 * 角色数据访问类
 * 
 * @author
 * 
 */
@Repository
public class RoleDao extends AbstractBaseDao<SysRole> {

	private Logger logger = Logger.getLogger(RoleDao.class);
	/** JDBC模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 获取全部用户
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysRole> getAllRoles() {
		String hql = "from SysGroup order by id asc";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	
	public Integer addObject(SysRole role) {
		Integer resultId = (Integer) this.getCurrentSession().save(role);
		return resultId;
	}
	
	//保存组织和角色关系
	public void addGroupRole(SysGroupRole sysGroupRole) {
		this.getCurrentSession().save(sysGroupRole);
	}
	//获取组织角色
	public SysGroupRole getGroupRole(int groupId ,int roleId) {
		String hql = "from SysGroupRole where 1=1 ";
		if(groupId>0){
			hql +=" and groupId = "+groupId;
		}
		if(roleId>0){
			hql +=" and roleId = "+roleId;
		}
		Query query = this.getCurrentSession().createQuery(hql);
		return (SysGroupRole) query.list().get(0);
	}
	//删除组织和角色关系
	public void delGroupRole(SysGroupRole sysGroupRole) {
		this.getCurrentSession().delete(sysGroupRole);
		this.getCurrentSession().flush();
	}
	//删除角色和菜单关系
	public void delRoleMenu(SysRoleMenu sysGroupMenu) {
		this.getCurrentSession().delete(sysGroupMenu);
		this.getCurrentSession().flush();
	}

	@Override
	public void updateObject(SysRole role) {
		this.getCurrentSession().save(role);
	}

	@Override
	public void deleteObject(SysRole role) {
		this.getCurrentSession().delete(role);
	}

	@Override
	public SysRole getObject(SysRole role) {
		
		return (SysRole) this.getCurrentSession().load(SysRole.class, role.getId());
	}
		
	public SysRole getRoleById(Integer id) {
		
		return (SysRole) this.getCurrentSession().load(SysRole.class, id);
	}

	/**
	 * 获取会员等级
	 * 
	 * @return
	 */
	public List<SysRole> findAppUserRole(Integer groupId) {
		// from SysGroup g join g.roles r where g.id=?
		String hql = "from SysRole where groupId = ";
		hql += groupId;
		return this.find(hql);
	}
	/**
	 * 获取非会员等级角色
	 * 
	 * @return
	 */
	public List<SysRole> findSysUserRole(Integer groupId) {
		// from SysGroup g join g.roles r where g.id=?
		String hql = "from SysRole where groupId <> ";
		hql += groupId;
		return this.find(hql);
	}

	/**
	 * 获取角色被使用次数
	 */
	public List<Map<String, Object>> getRoleBeenUsedCnt() {
		StringBuilder sqlBuff = new StringBuilder(500);
		sqlBuff.setLength(0);
		sqlBuff.append("select a.id, nvl(b.cnt, 0) as cnt ");
		sqlBuff.append("  from t_sys_role a ");
		sqlBuff.append("  left join (select role_id, count(1) cnt ");
		sqlBuff.append("               from t_sys_user_role ");
		sqlBuff.append("              group by role_id) b on a.id = b.role_id");

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlBuff
				.toString());

		return list;
	}

	/**
	 * 获取角色用户的个数
	 * 
	 * @param roleName
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @return
	 */
	public int getpage(String roleName, String startTime, String endTime,
			String userId, String provId) {
		// 登录用户对象
		StringBuilder sqlBuff = new StringBuilder(500);

		sqlBuff.setLength(0);
		sqlBuff.append("select count(1) ");
		sqlBuff.append("  from t_sys_role ");
		sqlBuff.append(" where 1=1 ");

		Object[] p = new Object[] {};

		// // 加入登录用户查看角色列表限制,超级管理员可以看到全部
		// if (!Const.SUPER_ADMIN_ID_LIST.contains(userId)) {
		// sqlBuff.append(" and update_user_id = ? ");
		// p = ArrayUtils.add(p, userId);
		// }

		if (StringUtils.isNotBlank(roleName)) {
			sqlBuff.append(" and ( name like ? or remark like ? )");
			p = ArrayUtils.add(p, "%" + roleName + "%");
			p = ArrayUtils.add(p, "%" + roleName + "%");
		}

		if (StringUtils.isNotBlank(startTime)) {
			sqlBuff.append(" and update_time >= ?");
			p = ArrayUtils.add(p, startTime + "000000");
		}

		if (StringUtils.isNotBlank(endTime)) {
			sqlBuff.append(" and update_time <= ?");
			p = ArrayUtils.add(p, endTime + "235959");
		}
		if (StringUtils.isNotBlank(provId)) {
			sqlBuff.append(" and rel_prov = ?");
			p = ArrayUtils.add(p, provId);
		}

		int num = jdbcTemplate.queryForInt(sqlBuff.toString(), p);
		return num;
	}

	/**
	 * 获取角色列表信息
	 * 
	 * @param roleName
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getRoleList(String roleName,
			String startTime, String endTime, String userId, String provId,
			int startNo, int endNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 登录用户对象
		StringBuilder sqlBuff = new StringBuilder(500);

		sqlBuff.setLength(0);

		sqlBuff.append("select role.id, ");
		sqlBuff.append("       role.name, ");
		sqlBuff.append("       role.remark, ");
		sqlBuff.append("       role.update_user_id, ");
		sqlBuff.append("       role.update_time, ");
		sqlBuff.append("       prov.prov_name ");
		sqlBuff.append("  from t_sys_role role, t_sys_province prov ");
		sqlBuff.append(" where 1 = 1 ");
		sqlBuff.append("   and prov.id = role.rel_prov");

		Object[] p = new Object[] {};
		//
		// // 加入登录用户查看角色列表限制,超级管理员可以看到全部
		// if (!Const.SUPER_ADMIN_ID_LIST.contains(userId)) {
		// sqlBuff.append(" and role.update_user_id = ? ");
		// p = ArrayUtils.add(p, userId);
		// }

		if (StringUtils.isNotBlank(roleName)) {
			sqlBuff.append(" and ( role.name like ? or role.remark like ? )");
			p = ArrayUtils.add(p, "%" + roleName + "%");
			p = ArrayUtils.add(p, "%" + roleName + "%");
		}

		if (StringUtils.isNotBlank(startTime)) {
			sqlBuff.append(" and role.update_time >= ?");
			p = ArrayUtils.add(p, startTime + "000000");
		}

		if (StringUtils.isNotBlank(endTime)) {
			sqlBuff.append(" and role.update_time <= ?");
			p = ArrayUtils.add(p, endTime + "235959");
		}
		if (StringUtils.isNotBlank(provId)) {
			sqlBuff.append(" and role.rel_prov like ? ");
			p = ArrayUtils.add(p, provId);
		}
		sqlBuff.append(" order by id desc ");

		// 加上分页参数
		p = ArrayUtils.add(p, endNo);
		p = ArrayUtils.add(p, startNo);

		try {
			list = jdbcTemplate.queryForList(
					SQLPagingUtils.getPagingSQL(sqlBuff.toString()), p);
		} catch (DataAccessException e) {
			logger.info("查询角色列表出现异常。", e);
			throw new SPlatformDaoException("查询角色列表出现异常。", e);
		}

		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
		}
		return list;
	}

	/**
	 * 获取所有角色列表信息
	 * 
	 * @return 角色列表
	 */
	public List<SysRole> getAllRoleList() {

		// 角色列表
		List<SysRole> roleList = new ArrayList<SysRole>();

		// 登录用户对象
		StringBuilder sqlBuff = new StringBuilder(500);

		sqlBuff.setLength(0);
		sqlBuff.append("select id, role_name, remark, operate_id, create_time, group_id");
		sqlBuff.append("  from t_sys_role ");

		try {
			roleList = jdbcTemplate.query(sqlBuff.toString(),
					new RowMapper<SysRole>() {
						public SysRole mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							SysRole role = new SysRole();
							role.setId(Integer.parseInt(rs.getString("id")));
							role.setRoleName(rs.getString("role_name"));
							role.setRemark(rs.getString("remark"));
							role.setOperateId(Integer.parseInt(rs.getString("operate_id")));
							role.setCreateTime(rs.getString("create_time"));
							//role.setGroupId(rs.getInt("group_id"));
							return role;
						}
					});
		} catch (DataAccessException e) {
			logger.info("查询角色信息。", e);
			throw new SPlatformDaoException("查询角色信息。", e);
		}

		return roleList;
	}

	/**
	 * 查找角色
	 * @param sRole
	 */
	public List<SysRole> findSysRole(Integer suRoleId) {
		String hql = "from SysRole where id = ";
		hql += suRoleId;
		return this.find(hql);
	}

	
	/**
	 * 获取角色对应操作的按钮
	 * @param roleId
	 * @return
	 */
	public List<Object> getRoleBtns(Integer roleId) {
		String sql = "select o.operate_code from t_sys_role_operate ro,t_sys_operate o where ro.operate_id = o.id and ro.role_id = ?";
		Object[] params = new Object[]{roleId};
		return this.querysqlList(sql, params);
	}

	/**
	 * 获取角色列表页面
	 * @param roleName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getRoles(String roleName, Integer pageNo, int pageSize) {

		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select r.id,r.role_name,r.remark,r.operate_id,r.create_time,r.group_id,r.checked,u.name operateName from t_sys_role r,t_sys_user u ");
		sbf.append(" where 1 = 1 and r.operate_id = u.uid ");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(roleName)){
			params = ArrayUtils.add(params, roleName);
			sbf.append(" and r.role_name = ?");
		}
		
		sbf.append(") as rt");
		return this.queryModelListByPage(sbf.toString(), params, pageNo, pageSize, SysRole.class);
	}


	/**
	 * 通过roleId获取菜单列表
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getRoleMenuList(int roleId) {
		StringBuilder sqlBuff = new StringBuilder();
		Object[] params = new Object[] {};

		sqlBuff.append("select id,");
		sqlBuff.append("       menu_name,");
		sqlBuff.append("       menu_code,");
		sqlBuff.append("       menu_pid,");
		sqlBuff.append("       menu_url,");
		sqlBuff.append("       leaf_yn,");
		sqlBuff.append("       menu_btns,");
		sqlBuff.append("       icon_tag,");
		sqlBuff.append("       has_child ");
		sqlBuff.append("  from t_sys_menu");
		sqlBuff.append(" where 1=1 ");// 获得后台管理菜单

		if(roleId > 0){
			sqlBuff.append(" and id in (select menu_id from t_sys_role_menu where role_id = ?)");
			params = ArrayUtils.add(params, roleId);
		}

		sqlBuff.append("order by id ");
		
		//oracle使用
//		sqlBuff.append("  start with   menu_pcode = '0' ");
//		sqlBuff.append("connect by  menu_pcode=PRIOR menu_code ");
//		sqlBuff.append("order by menu_code ");

		return jdbcTemplate.queryForList(sqlBuff.toString(), params);
	}

	/**
	 * 
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public SysRoleMenu getRoleMenu(Integer menuId, int roleId) {
		String hql = "from SysRoleMenu where 1=1 ";
		if(menuId>0){
			hql +=" and menuId = "+menuId;
		}
		if(roleId>0){
			hql +=" and roleId = "+roleId;
		}
		Query query = this.getCurrentSession().createQuery(hql);
		return (SysRoleMenu) query.list().get(0);
	}

	/**
	 * 添加角色菜单
	 * @param sysRoleMenu
	 */
	public void addRoleMenu(SysRoleMenu sysRoleMenu) {
		this.getCurrentSession().save(sysRoleMenu);
	}
	
	

}
