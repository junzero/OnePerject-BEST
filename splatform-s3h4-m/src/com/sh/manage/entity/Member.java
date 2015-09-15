package com.sh.manage.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "T_Member", schema = "SPLATFORM_DB")
@JsonIgnoreProperties({"vipcards","hibernateLazyInitializer","handler","fieldHandler"})
public class Member implements Serializable{
	
	private static final long serialVersionUID = 7488904616028381007L;
	
	@Id
	@GeneratedValue(generator = "suserGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "suserGenerator", strategy = "native")
	@Column(name = "id", length = 8)
	private Integer id;
	/**
	 * 邮箱
	 */
	@Column(name = "email", length = 100)
	private String email;
	
	/**
	 * 姓名
	 */
	@Column(name = "name", length = 20, nullable=false)
	private String name;
	
	/**
	 * 生日
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="birthday")
	private Date birthday;
	
	/**
	 * 性别
	 */
	@Column(name="sex", length=1)
	private String sex;
	
	/**
	 * 手机号码
	 */
	@Column(name="mobile", length=20)
	private String mobile;
	/**
	 * 头像
	 */
	@Column(name="avatar", length=225)
	private String avatar;
	
	/**
	 * 固定电话
	 */
	@Column(name="phone", length=20)
	private String phone;
	
	/**
	 * 所在省
	 */
	@Column(name="province", length=10)
	private String province;
	
	/**
	 * 所在市
	 */
	@Column(name="city", length=10)
	private String city;
	
	/**
	 * 详细地址
	 */
	@Column(name="address", length=100)
	private String address;
	
	/**
	 * 所属门店
	 */
	@Column(name="store_id", length=8)
	private String storeId;
	
	/**
	 * 会员等级 或者卡等级
	 */
	@Column(name="member_level", length=1)
	private String memberLevel;
	
	/**
	 * 积分
	 */
	@Column(name="point", length=8)
	private Integer point;
	
	
	/**
	 * 状态
	 */
	@Column(name="status", length=1)
	private String status;

	@Column(name="created")
	private String created;
	
	@OneToMany(mappedBy="member")
	private List<Vipcard> vipcards;
	
	public List<Vipcard> getVipcards() {
		return vipcards;
	}

	public void setVipcards(List<Vipcard> vipcards) {
		this.vipcards = vipcards;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	
}
