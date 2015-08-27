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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.AppUser;
import com.sh.manage.entity.SysGroupRole;
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
public class SysRoleDao extends AbstractBaseDao<SysRoleMenu> {

	private Logger logger = Logger.getLogger(SysRoleDao.class);


	public void addSysRoleMenu(SysRoleMenu sRoleMenu) {
		this.save(sRoleMenu);
	}


	@Override
	public Integer addObject(SysRoleMenu clazz) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateObject(SysRoleMenu clazz) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteObject(SysRoleMenu clazz) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public SysRoleMenu getObject(SysRoleMenu clazz) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
