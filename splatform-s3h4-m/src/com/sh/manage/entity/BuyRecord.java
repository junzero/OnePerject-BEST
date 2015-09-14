package com.sh.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BUY_RECORD", schema = "SPLATFORM_DB")
public class BuyRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2389972101687368859L;
	
	@Id
	@GeneratedValue(generator = "suserGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "suserGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Integer id;
	
	@Column(name="member_id",length=8)
	private Integer memberId;
	
	@Column(name="card_num",length=20)
	private String cardNum;
	
	@Column(name="member_name",length=20)
	private String memberName;
	
	@Column(name="total_price")
	private BigDecimal totalPrice;
	
	@Column(name="buy_time")
	@Temporal(TemporalType.DATE)
	private Date buyTime;
	
	@Column(name="point")
	private Integer point;
	
	@Column(name="remark",length=256)
	private String remark;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIME)
	private Date createdDate;
	
	@Column(name="create_user",length=20)
	private String createUser;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="record_id")
	private List<BuyRecordItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public List<BuyRecordItem> getItems() {
		return items;
	}

	public void setItems(List<BuyRecordItem> items) {
		this.items = items;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
}
