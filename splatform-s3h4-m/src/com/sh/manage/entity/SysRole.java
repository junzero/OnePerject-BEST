package com.sh.manage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author
 * 
 */

@Entity
@Table(name = "T_SYS_ROLE", schema = "SPLATFORM_DB")
public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488904616028381006L;

	@Id
	@GeneratedValue(generator="sroleGenerator",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "sroleGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private int id;

	@Column(name = "role_name", length = 18)
	private String roleName;

	@Column(name = "remark", length = 50)
	private String remark;

	@Column(name = "operate_id", length = 8)
	private int operateId;

	@Column(name = "create_time", length = 20)
	private String createTime;
	
	private String operateName;
	
	@Transient
	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	/**
	 * 是否已经选择
	 */
	private boolean checked;
	/**
	 * 角色列表
	 * 
	 * */
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER,
			mappedBy = "roleList")
	@OrderBy("id ASC")
	private List<SysUser> userList = new ArrayList<SysUser>();
	

////	@ManyToOne(cascade = CascadeType.ALL, optional = false)
////	//@JoinTable(name = "t_sys_group_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns = { @JoinColumn(name = "group_id") })
////	@JoinColumn(name = "id", referencedColumnName = "group_id")
//	// 外键为id，与t_sys_group_role中的group_id关联
//	@Column(name = "group_id",length=8)
//	private int groupId;
	
	/**
	 * 组织列表
	 * 
	 * */
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "t_sys_group_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
	@OrderBy("id ASC")
	private Set<SysGroup> groups;
	
	
	/**
	 * 角色操作多对多
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "t_sys_role_operate", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "operate_id") })
	@OrderBy("id ASC")
	private Set<SysOperate> operateSet = new HashSet<SysOperate>();
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "t_sys_role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@OrderBy("id ASC")
	private Set<SysMenu> menuSet;
	
	
	
	public Set<SysMenu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<SysMenu> menuSet) {
		this.menuSet = menuSet;
	}

	public List<SysUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SysUser> userList) {
		this.userList = userList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOperateId() {
		return operateId;
	}

	public void setOperateId(int operateId) {
		this.operateId = operateId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

//	public int getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(int groupId) {
//		this.groupId = groupId;
//	}

	

	public Set<SysOperate> getOperateSet() {
		return operateSet;
	}

	public void setOperateSet(Set<SysOperate> operateSet) {
		this.operateSet = operateSet;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Set<SysGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<SysGroup> groups) {
		this.groups = groups;
	}
}
