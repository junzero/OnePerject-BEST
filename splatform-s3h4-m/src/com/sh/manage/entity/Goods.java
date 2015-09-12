package com.sh.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 消费项目
 * @author 123
 *
 */
@Entity
@Table(name = "T_GOODS", schema = "SPLATFORM_DB")
public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4437342300744096112L;

	@Id
	@GeneratedValue(generator = "suserGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "suserGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Integer id;
	
	/**
	 * 姓名
	 */
	@Column(name = "name", length = 20, nullable=false)
	private String name;
	
	/**
	 * 描述
	 */
	@Lob
	@Column(name = "description" )
	private String description;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="type",length=1)
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}	
