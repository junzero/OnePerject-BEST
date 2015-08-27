/**
 * 
 */
package com.sh.manage.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.constants.Constants;
import com.sh.manage.constants.SessionConstants;
import com.sh.manage.entity.SysMenu;
import com.sh.manage.entity.SysRole;
import com.sh.manage.pojo.LoginUser;

/**
 * 角色数据访问类
 * @author 
 * 
 */
@Repository
public class MenuDao extends AbstractBaseDao<SysMenu> {

	/**
	 * 获取全部用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getAllMenus() {
		String hql = "from SysGroup order by id asc";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public Integer addObject(SysMenu menu) {
		Integer resultId = (Integer) this.getCurrentSession().save(menu);
		this.getCurrentSession().flush();
		return resultId;
	}

	@Override
	public void updateObject(SysMenu menu) {
		this.getCurrentSession().save(menu);
	}

	@Override
	public void deleteObject(SysMenu menu) {
		this.getCurrentSession().delete(menu);
	}

	@Override
	public SysMenu getObject(SysMenu menu) {
		String hql = "from SysMenu where id=?";
		hql+=menu.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		return (SysMenu) query.list().get(0);
	}

	
	/**
	 * 获取当前用户一级菜单数据
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getMenuList(LoginUser loginUser) {
		StringBuffer sql = new StringBuffer();
		Object[] params = new Object[] {};
		sql.append("select m.id,m.menu_code,m.menu_name,m.menu_pid,m.menu_url,m.leaf_yn,m.menu_btns,m.icon_tag,m.has_child");
		sql.append(" from t_sys_menu m");
		sql.append(" where 1=1");
		sql.append(" and m.menu_pid = ?");
		//一级菜单
		params = ArrayUtils.add(params, Constants.SYS_TYPE_0);
		
		//不是超级管理员需要查询属于自己权限的菜单
		// 如果是超级管理员则可以查看到所有菜单权限
		if (!SessionConstants.SUPER_ADMIN_ID_LIST.contains(""+loginUser.getId())) {
			sql.append(" and m.id in ( ");
			sql.append("select distinct menu_id");
			sql.append("  from t_sys_role_menu");
			sql.append(" where role_id in (select gr.role_id from t_sys_group_role gr,t_sys_user u WHERE gr.group_id = u.group_id AND u.uid = ? ) ");
			sql.append(" ) ");

			params = ArrayUtils.add(params, loginUser.getId());
		}else{
			//logger.info("超级管理员...");
		}		
		// 如果是超级管理员则可以查看到所有菜单权限
		//oracle专用
//		sql.append(" start with m.menu_pid = '0'");
//		sql.append(" connect by m.menu_pid = PRIOR m.id");
		sql.append(" order by m.id");		 
		
		return (List<SysMenu>) this.queryModelSqlList(sql.toString(), params,SysMenu.class);
	}

	/**
	 * 根据Pid查询子菜单
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getMenuListByPId(int i) {
		StringBuffer sql = new StringBuffer();
		Object[] params = new Object[] {};
		sql.append("select m.id,m.menu_code,m.menu_name,m.menu_pid,m.menu_url,m.leaf_yn,m.menu_btns,m.icon_tag,m.has_child");
		sql.append(" from t_sys_menu m");
		sql.append(" where 1=1");
		sql.append(" and m.menu_pid = ?");
		//后台菜单
//		params = ArrayUtils.add(params, Const.SYS_TYPE_0);
		params = ArrayUtils.add(params, i);
		
		return (List<SysMenu>) queryModelSqlList(sql.toString(),params,SysMenu.class);
	}

	/**
	 * 获取所有菜单
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getAllMenuList() {
		StringBuffer sql = new StringBuffer();
		Object[] params = new Object[] {};
		sql.append("select m.id,m.menu_name,m.menu_code,m.menu_pid,m.menu_url,m.leaf_yn,m.menu_btns,m.icon_tag,m.has_child");
		sql.append(" from t_sys_menu m");
		sql.append(" where 1=1");
		//后台菜单

		return (List<SysMenu>) queryModelSqlList(sql.toString(),params,SysMenu.class);
	}


}
