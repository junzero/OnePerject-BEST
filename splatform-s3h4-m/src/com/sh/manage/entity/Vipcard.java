package com.sh.manage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_VIPCARD", schema = "SPLATFORM_DB")
public class Vipcard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8639215960969608683L;
	@Id
	@GeneratedValue(generator = "suserGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "suserGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Integer id;
	
	/**
	 * 会员卡号
	 */
	@Column(name="card_num", length=20)
	private String cardNum;
	
	/**
	 * 余额
	 */
	@Column(name="balance", length=20)
	private String balance;
	
	/**
	 * 状态
	 */
	@Column(name="status", length=1)
	private String status;
	
	/**
	 * 开通时间
	 */
	@Column(name="open_time", length=20)
	private String openTime;
	
	/**
	 * 截止日期
	 */
	@Column(name="deadline", length=20)
	private String deadline;
	
	/**
	 * 密码如果有
	 */
	@Column(name="password", length=20)
	private String password;
	
	/**
	 * 添加时间
	 */
	@Column(name="created_time", length=20)
	private String createdTime;
	
	/**
	 * 会员卡类型
	 */
	@Column(name="type", length=1)
	private String type;

	@Column(name="member_id", length=8)
	private Integer memberId;
	
	@Transient
	private String memberName;
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
