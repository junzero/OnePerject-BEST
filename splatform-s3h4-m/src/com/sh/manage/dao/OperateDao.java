/**
 * 
 */
package com.sh.manage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.SysOperate;
import com.sh.manage.entity.SysRole;

/**
 * 角色数据访问类
 * 
 * @author
 * 
 */
@Repository
public class OperateDao extends AbstractBaseDao<SysOperate> {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(OperateDao.class);

	
	
	
	/**
	 * 查找操作
	 * @param operateId
	 */
	public List<SysOperate> findSysOperate(String model_code) {
		String hql = "from SysOperate where operateCode = '";
		hql += model_code+"'";
		return this.find(hql);
	}




	public Integer addObject(SysOperate clazz) {
		Integer resultId = (Integer) this.getCurrentSession().save(clazz);
		this.getCurrentSession().flush();
		return resultId;
	}




	@Override
	public void updateObject(SysOperate clazz) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void deleteObject(SysOperate clazz) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public SysOperate getObject(SysOperate clazz) {
		// TODO Auto-generated method stub
		return null;
	}
}
