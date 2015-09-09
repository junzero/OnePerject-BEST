package com.sh.manage.dao;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.Vipcard;
import com.sh.manage.module.page.Page;

@Repository
public class VipcardDao extends AbstractBaseDao<Vipcard>{

	@Override
	public Integer addObject(Vipcard vipcard) {
		return (Integer) this.getCurrentSession().save(vipcard);
	}

	@Override
	public void updateObject(Vipcard vipcard) {
		this.getCurrentSession().save(vipcard);
		
	}

	@Override
	public void deleteObject(Vipcard vipcard) {
		this.getCurrentSession().delete(vipcard);
	}

	@Override
	public Vipcard getObject(Vipcard vipcard) {
		String hql = "from Member where cardNum = ";
		hql += vipcard.getCardNum();
		Query query = this.getCurrentSession().createQuery(hql);
		return (Vipcard) query.list().get(0);
	}
	
	/**
	 * 获取所有会员卡
	 * @param name
	 * @param mobile
	 * @param cardNum
	 * @return
	 */
	public Page getAllCard(String cardNum,String status,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select v.id,v.member_id,v.type,v.password,v.deadline,v.open_time,v.created_time,v.balance,v.status,v.card_num,m.name memberName "
				+ "from t_vipcard v left join t_member m on v.member_id=m.id "
				+ "where 1=1");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(cardNum)){
			params = ArrayUtils.add(params, "%"+cardNum+"%");
			sbf.append(" and s.card_num like ?");
		}
		
		if(!StringUtils.isEmpty(status)){
			params = ArrayUtils.add(params, status);
			sbf.append(" and s.status = ?");
		}
		
		sbf.append(") as rt");
		return this.queryModelListByPage(sbf.toString(), params, pageNo, pageSize, Vipcard.class);
	}

}
