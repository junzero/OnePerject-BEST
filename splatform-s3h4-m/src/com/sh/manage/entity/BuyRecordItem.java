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
	
	
}
