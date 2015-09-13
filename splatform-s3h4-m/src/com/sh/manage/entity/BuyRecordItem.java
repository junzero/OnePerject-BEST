package com.sh.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BUY_RECORD_ITEM", schema = "SPLATFORM_DB")
public class BuyRecordItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 407319945906100881L;
	
	@Id
	@GeneratedValue(generator = "suserGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "suserGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Integer id;
	
	@Column(name = "record_id",length=8)
	private Integer recordId;
	
	@Column(name = "goods_id",length=8)
	private Integer goodsId;
	
	@Column(name = "goods_name",length=20)
	private String goodsName;
	
	@Column(name="price")
	private BigDecimal pirce;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="total_price")
	private BigDecimal totalPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPirce() {
		return pirce;
	}

	public void setPirce(BigDecimal pirce) {
		this.pirce = pirce;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
