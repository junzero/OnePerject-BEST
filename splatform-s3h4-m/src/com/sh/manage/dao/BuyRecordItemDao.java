package com.sh.manage.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.BuyRecordItem;

@Repository
public class BuyRecordItemDao extends AbstractBaseDao<BuyRecordItem>{

	@Override
	public Integer addObject(BuyRecordItem item) {
		return (Integer) this.getCurrentSession().save(item);
	}

	@Override
	public void updateObject(BuyRecordItem item) {
		this.getCurrentSession().save(item);
		
	}

	@Override
	public void deleteObject(BuyRecordItem item) {
		this.getCurrentSession().delete(item);
	}

	@Override
	public BuyRecordItem getObject(BuyRecordItem item) {
		String hql = "from BuyRecordItem where id = ";
		hql += item.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		if(query.list().size()>0){
			return (BuyRecordItem) query.list().get(0);
		}
		return null;
	}

}
