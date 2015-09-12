package com.sh.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name="total_price")
	private BigDecimal totalPrice;
	
	@Column(name="buy_time")
	private Date buyTime;
	
	@Column(name="remark",length=256)
	private String remark;
	
	@Column(name="created_date",length=256)
	private Date createdDate;
	
	@Column(name="create_user",length=256)
	private String createUser;
	
	@Transient
	private List<BuyRecordItem> items;
	
	
}
