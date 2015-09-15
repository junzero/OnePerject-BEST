package com.sh.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column(name="balance")
	private BigDecimal balance;
	
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "id")  
	private Member member;
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
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
