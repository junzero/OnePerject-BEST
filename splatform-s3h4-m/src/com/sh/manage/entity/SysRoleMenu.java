package com.sh.manage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * @author
 * 
 */

@Entity
@Table(name = "T_SYS_ROLE_MENU", schema = "SPLATFORM_DB")
public class SysRoleMenu implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6799352642343867596L;

	//oracle
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ROLE_MENU")     
//    @SequenceGenerator(name="SEQ_ROLE_MENU",allocationSize=1,initialValue=1, sequenceName="SEQ_ROLE_MENU")
//	@Column(name = "id", length = 8)
	
	@Id
	@GeneratedValue(generator="sroleGenerator",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "sroleGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Long id;
	
	@Column(name = "role_id", length = 14)
	private Integer roleId;

	@Column(name = "menu_id", length = 14)
	private Integer menuId;
	
	@Column(name = "menu_btn", length = 30)
	private String menuBtn;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuBtn() {
		return menuBtn;
	}

	public void setMenuBtn(String menuBtn) {
		this.menuBtn = menuBtn;
	}
}
