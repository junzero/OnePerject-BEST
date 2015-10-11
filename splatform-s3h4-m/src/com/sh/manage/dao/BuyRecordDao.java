package com.sh.manage.dao;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.BuyRecord;
import com.sh.manage.module.page.Page;

@Repository
public class BuyRecordDao extends AbstractBaseDao<BuyRecord>{

	@Override
	public Integer addObject(BuyRecord buyRecord) {
		return (Integer) this.getCurrentSession().save(buyRecord);
	}

	@Override
	public void updateObject(BuyRecord buyRecord) {
		this.getCurrentSession().save(buyRecord);
		
	}

	@Override
	public void deleteObject(BuyRecord buyRecord) {
		this.getCurrentSession().delete(buyRecord);
	}

	@Override
	public BuyRecord getObject(BuyRecord buyRecord) {
		String hql = "from BuyRecord where id = ";
		hql += buyRecord.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		if(query.list().size()>0){
			return (BuyRecord) query.list().get(0);
		}
		return null;
	}
	
	/**
	 * @param name
	 * @param mobile
	 * @param cardNum
	 * @return
	 */
	public Page getAllBuyRecord(String cardNum,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from BuyRecord "
				+ "where 1=1");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(cardNum)){
			params = ArrayUtils.add(params, "%"+cardNum+"%");
			sbf.append(" and cardNum like ?");
		}
		
		
		sbf.append(" order by buyTime desc,id desc");
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}
	
	public Page getAllByMemberId(Integer memberId, int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from BuyRecord where 1=1");
		
		Object[] params = new Object[]{};
		
		if(memberId != null){
			params = ArrayUtils.add(params, memberId);
			sbf.append(" and memberId = ?");
		}
		
		sbf.append("order by buyTime desc");
		
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}

}
