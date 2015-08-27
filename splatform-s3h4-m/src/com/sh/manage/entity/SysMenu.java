package com.sh.manage.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author 
 * 
 */

@Entity
@Table(name = "T_SYS_MENU", schema = "SPLATFORM_DB")
public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488904616028381006L;
	
	@Id
	@GeneratedValue(generator="smenuGenerator",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "smenuGenerator", strategy = "native")

	@Column(name = "id", length = 8)
	private Integer id;

	@Column(name = "menu_name", length = 20)
	private String menuName;
	
	@Column(name = "menu_code", length = 20)
	private String menuCode;
	
	@Column(name = "menu_pid", length = 8)
	private int menuPid;
	
	
	@Column(name = "menu_url", length = 8)
	private String menuUrl;
	
	@Column(name = "leaf_yn", length = 1)
	private int leafYn;
	
	@Column(name = "menu_btns", length = 50)
	private String menuBtns;
	
	@Column(name = "icon_tag", length = 20)
	private String iconTag;
	
	@Column(name = "has_child", length = 1)
	private int hasChild;

	/**
	 * 操作角色多对多
	 */
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER,
			mappedBy = "menuSet")
	@OrderBy("id ASC")
	private Set<SysRole> roleSet = new HashSet<SysRole>();

	public Set<SysRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<SysRole> roleSet) {
		this.roleSet = roleSet;
	}

	/**
	 * 菜单列表
	 * 
//	 * */
//	@ManyToMany(mappedBy = "menuSet", fetch = FetchType.LAZY)
//	@OrderBy("id ASC")
//	private List<SysGroup> groupList;
	
//	
//	public List<SysGroup> getGroupList() {
//		return groupList;
//	}
//
//	public void setGroupList(List<SysGroup> groupList) {
//		this.groupList = groupList;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getLeafYn() {
		return leafYn;
	}

	public void setLeafYn(int leafYn) {
		this.leafYn = leafYn;
	}

	public String getMenuBtns() {
		return menuBtns;
	}

	public void setMenuBtns(String menuBtns) {
		this.menuBtns = menuBtns;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(int menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getIconTag() {
		return iconTag;
	}

	public void setIconTag(String iconTag) {
		this.iconTag = iconTag;
	}

	public int getHasChild() {
		return hasChild;
	}

	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}

}
