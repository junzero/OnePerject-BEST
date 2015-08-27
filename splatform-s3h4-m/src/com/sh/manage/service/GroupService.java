/**
 * 
 */
package com.sh.manage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.manage.dao.GroupDao;
import com.sh.manage.dao.RoleDao;
import com.sh.manage.entity.AppUser;
import com.sh.manage.entity.SysGroup;
import com.sh.manage.entity.SysGroupRole;
import com.sh.manage.entity.SysRole;
import com.sh.manage.exception.SPlatformServiceException;
import com.sh.manage.module.page.Page;


/**
 * @author 
 *
 */
@Service
public class GroupService extends BaseService{
	
	private Logger logger = Logger.getLogger(GroupService.class);

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private RoleDao roleDao;
	/**
	 * 查询所有
	 * @return
	 */
	public List<SysGroup> findAll(){
		return groupDao.getAllGroups();
	}

	/**
	 * 添加组
	 * @param group
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {SPlatformServiceException.class})
	public void addGroup(SysGroup group,String roleStr) {
		groupDao.addObject(group);
		//处理组织对应角色
		String[] roleArr = roleStr.split(",");
		if(roleArr.length>0){
			//保存提交的
			for(String roleId : roleArr){
				SysGroupRole sysGroupRole = new SysGroupRole();
				sysGroupRole.setGroupId(group.getId());
				sysGroupRole.setRoleId(Integer.parseInt(roleId));
				roleDao.addGroupRole(sysGroupRole);
			}
		}
		
	}
	/**
	 * 更新组
	 * @param group
	 * @return
	 */
	public boolean updateGroup(SysGroup group) {
		groupDao.update(group);
		return false;
	}

	/**
	 * 获取单个组
	 * @param group
	 * @return
	 */
	public AppUser getOneGroup(SysGroup group) {
		return null;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {SPlatformServiceException.class})
	public boolean delGroup(SysGroup group) throws SPlatformServiceException {
		
		//删除组织
		
		Set<SysRole> delRoleSet = group.getRoles();
		List delList = new ArrayList();
		for(SysRole role:delRoleSet){
			//清除引用
			delList.add(role);
			role.getGroups().remove(group);
//			group.getRoles().remove(role);
			
		}
		delRoleSet.removeAll(delList);
		
		groupDao.delete(group);
		return false;
	};
	
	
	
	/**
	 * 获取组对应角色信息
	 */
	public List<SysRole> getGroupRole(SysGroup sysGroup){
		List<SysRole> sysRoleList = groupDao.getRoleRole(sysGroup);
		return sysRoleList;
	}

	/**
	 * 获取对应的角色列表
	 * @param pageSize 
	 * @param pageNo 
	 */
	public Page getGroupRoles(Integer groupIndex, Integer pageNo, int pageSize) {
		Page page = groupDao.getGroupRoles(groupIndex,pageNo,pageSize);
		return page;
	}

	/**
	 * 组织信息i修改
	 * @param group
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {SPlatformServiceException.class})
	public void updateGroupInfo(SysGroup group,String roleStr) {
		//更新组织信息
		groupDao.update(group);
		//处理组织对应角色
		String[] roleArr = roleStr.split(",");
		if(roleArr.length>0){
			//清除之前的数据
			Set<SysRole> roles = group.getRoles();
			Iterator<SysRole> iter = roles.iterator();
			while (iter.hasNext()) {
				SysRole role = (SysRole)iter.next();
				SysGroupRole sysGroupRole = new SysGroupRole();
				sysGroupRole.setRoleId(role.getId());
				sysGroupRole.setGroupId(group.getId());
				SysGroupRole dbSysGroupRole = roleDao.getGroupRole(group.getId(), role.getId());
				if(null != dbSysGroupRole){
					//删除数据库中的关系
					roleDao.delGroupRole(dbSysGroupRole);
				}
			}
			
			//保存提交的
			for(String roleId : roleArr){
				SysGroupRole sysGroupRole = new SysGroupRole();
				sysGroupRole.setGroupId(group.getId());
				sysGroupRole.setRoleId(Integer.parseInt(roleId));
				roleDao.addGroupRole(sysGroupRole);
			}
		}
	}
	
	
	/**
	 * 查询组织
	 * @param groupId
	 * @return
	 * @throws SPlatformServiceException 
	 */
	public SysGroup findSysGroup(Integer groupId) throws SPlatformServiceException{
		try {
			List<SysGroup> sysGroupList = groupDao.findSysGroup(groupId);
			//找到了组织
			if(null != sysGroupList){
				return sysGroupList.get(0);
			}
			//找不到组织
			return new SysGroup();
		} catch (Exception e) {
			logger.error("service:查询组织信息出现异常", e);
			throw new SPlatformServiceException("查询组织信息出现异常");
		}
	}

	/**
	 * 获取组织
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getGroups(String groupName,Integer pageNo, int pageSize) {
		Page page = groupDao.getGroups(groupName,pageNo,pageSize);
		return page;
	}

	/**
	 * 获取所有组织
	 * @return
	 */
	public List<SysGroup> getAllGroupList() {
		List<SysGroup> groups = groupDao.getAllGroups();
		if(null != groups && groups.size() > 0){
			return groups;
		}
		return new ArrayList<SysGroup>();
	}
}
