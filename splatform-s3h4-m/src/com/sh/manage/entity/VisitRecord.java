package com.sh.manage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_VISIT_RECORD", schema = "SPLATFORM_DB")
public class VisitRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1045212549573907951L;
	
	@Id
	@GeneratedValue(generator = "suserGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "suserGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Integer id;
	
	@Column(name = "visitor_name", length = 10)
	private String visitorName;
	
	@Column(name = "visit_time", length = 20)
	private String visitTime;

	@Column(name = "visited_name", length = 10)
	private String visitedName;
	
	@Column(name = "reason", length = 512)
	private String reason;
	
	@Column(name = "leave_time", length = 20)
	private String leaveTime;
	
	@Column(name = "created_time", length = 20)
	private String createdTime;
	
	@Column(name = "created_user", length = 20)
	private String createdUser;

	@Column(name = "mobile",length=20)
	private String mobile;
	
	@Column(name = "idcard",length=20)
	private String idcard;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitedName() {
		return visitedName;
	}

	public void setVisitedName(String visitedName) {
		this.visitedName = visitedName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	
}
