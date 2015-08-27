/**
 * 
 */
package com.sh.manage.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.AppUser;
import com.sh.manage.entity.SysRole;
import com.sh.manage.entity.SysUser;
import com.sh.manage.module.page.Page;

/**
 * 用户数据访问类
 * 
 * @author
 * 
 */
@Repository
public class AppUserDao extends AbstractBaseDao<AppUser> {

	
	
	/**
	 * 获取全部会员
	 * @param pageSize 
	 * @param pageNo 
	 * 
	 * @return
	 */
	public Page getAllAppUser(Integer groupId,String username,String startDate,String endDate,int status,int pageNo, int pageSize) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select s.auid,s.email,s.name,s.username,s.password,s.terminal_id,g.group_name groupName,s.status,s.start_date,s.end_date,s.group_id,s.last_login_ip,s.limit_year,s.remark from t_sh_user s,t_sys_group g ");
		sbf.append(" where 1 = 1 and s.group_id = g.id ");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(username)){
			params = ArrayUtils.add(params, username);
			sbf.append(" and s.username = ?");
		}
		if(!StringUtils.isEmpty(startDate)){
			params = ArrayUtils.add(params, startDate);
			sbf.append(" and s.start_date >= ?");
		}
		if(!StringUtils.isEmpty(endDate)){
			params = ArrayUtils.add(params, endDate);
			sbf.append(" and s.end_date <= ?");
		}
		if(status > 0){
			params = ArrayUtils.add(params, status);
			sbf.append(" and s.status = ?");
		}
		if(groupId > 0){
			params = ArrayUtils.add(params, groupId);
			sbf.append(" and s.group_id = ?");
		}
		sbf.append(") as rt");
		return this.queryModelListByPage(sbf.toString(), params, pageNo, pageSize, AppUser.class);
	}

	
	
	
	
	
	
	
	@Override
	public Integer addObject(AppUser user) {
		return (Integer) this.getCurrentSession().save(user);
	}

	@Override
	public void updateObject(AppUser user) {
		this.getCurrentSession().save(user);
	}

	@Override
	public void deleteObject(AppUser user) {
		this.getCurrentSession().delete(user);
	}

	@Override
	public AppUser getObject(AppUser user) {
		String hql = "from User where email=?";
		hql += user.getEmail();
		Query query = this.getCurrentSession().createQuery(hql);
		return (AppUser) query.list().get(0);
	}

	/**
	 * 用户角色列表
	 */
	public List<SysRole> getUserRole(SysUser sysUser) {
		return null;
	}

	/**
	 * 查找某个会员
	 * @param auid
	 */
	public List<AppUser> findAppUser(Integer auid) {
		String hql = "from AppUser where auid = ";
		hql += auid;
		return this.find(hql);
	}
	
}
