package com.sh.manage.dao;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.Member;
import com.sh.manage.module.page.Page;

@Repository
public class MemberDao extends AbstractBaseDao<Member>{

	@Override
	public Integer addObject(Member member) {
		return (Integer) this.getCurrentSession().save(member);
	}

	@Override
	public void updateObject(Member member) {
		this.getCurrentSession().save(member);
		
	}

	@Override
	public void deleteObject(Member member) {
		this.getCurrentSession().delete(member);
	}

	@Override
	public Member getObject(Member member) {
		String hql = "from Member where id= ";
		hql += member.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		return (Member) query.list().get(0);
	}

	/**
	 * 获取所有会员
	 * @param name
	 * @param mobile
	 * @param cardNum
	 * @return
	 */
	public Page getAllMember(String name,String mobile,String cardNum,String status,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select s.id,s.avatar,s.birthday,s.email,s.name,s.address,s.mobile,s.card_num,s.card_created,s.card_deadline,s.status,s.sex,s.phone,s.province,s.city,s.group_id,s.card_password,s.member_level,s.point,s.balance,s.created,s.create_user from t_member s");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(name)){
			params = ArrayUtils.add(params, "%"+name+"%");
			sbf.append(" and s.name like");
		}
		if(!StringUtils.isEmpty(mobile)){
			params = ArrayUtils.add(params, "%"+mobile+"%");
			sbf.append(" and s.mobile like ?");
		}
		if(!StringUtils.isEmpty(cardNum)){
			params = ArrayUtils.add(params, "%"+cardNum+"%");
			sbf.append(" and s.card_num like ?");
		}
		
		if(!StringUtils.isEmpty(status)){
			params = ArrayUtils.add(params, status);
			sbf.append(" and s.status = ?");
		}
		
		sbf.append(") as rt");
		
		return this.queryModelListByPage(sbf.toString(), params, pageNo, pageSize, Member.class);
		
	}
}
