package com.sh.manage.dao;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.Recharge;
import com.sh.manage.module.page.Page;

@Repository
public class RechargeDao extends AbstractBaseDao<Recharge>{

	@Override
	public Integer addObject(Recharge recharge) {
		return (Integer) this.getCurrentSession().save(recharge);
	}

	@Override
	public void updateObject(Recharge recharge) {
		this.getCurrentSession().save(recharge);
		
	}

	@Override
	public void deleteObject(Recharge recharge) {
		this.getCurrentSession().delete(recharge);
	}

	@Override
	public Recharge getObject(Recharge recharge) {
		String hql = "from Recharge where id = ";
		hql += recharge.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		if(query.list().size()>0){
			return (Recharge) query.list().get(0);
		}
		return null;
	}
	
	/**
	 * @param name
	 * @param mobile
	 * @param cardNum
	 * @return
	 */
	public Page getAllRecharge(String cardNum,String mobile,String memberName,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from Recharge "
				+ "where 1=1");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(cardNum)){
			params = ArrayUtils.add(params, "%"+cardNum+"%");
			sbf.append(" and cardNum like ?");
		}
		if(!StringUtils.isEmpty(mobile)){
			params = ArrayUtils.add(params, "%"+mobile+"%");
			sbf.append(" and mobile like ?");
		}
		if(!StringUtils.isEmpty(memberName)){
			params = ArrayUtils.add(params, "%"+memberName+"%");
			sbf.append(" and memberName like ?");
		}
		
		sbf.append(" order by createdDate desc,id desc");
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}
	
	public Page getAllByMemberId(Integer memberId,int pageNo,int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from Recharge where 1=1");
		
		Object[] params = new Object[]{};
		
		if(memberId != null){
			params = ArrayUtils.add(params, memberId);
			sbf.append(" and memberId = ?");
		}
		
		sbf.append("order by createdDate desc");
		
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}

}
