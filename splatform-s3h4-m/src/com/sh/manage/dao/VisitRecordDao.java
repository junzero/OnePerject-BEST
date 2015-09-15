package com.sh.manage.dao;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.VisitRecord;
import com.sh.manage.module.page.Page;

@Repository
public class VisitRecordDao extends AbstractBaseDao<VisitRecord>{

	@Override
	public Integer addObject(VisitRecord visitRecord) {
		return (Integer) this.getCurrentSession().save(visitRecord);
	}

	@Override
	public void updateObject(VisitRecord visitRecord) {
		this.getCurrentSession().save(visitRecord);
		
	}

	@Override
	public void deleteObject(VisitRecord visitRecord) {
		this.getCurrentSession().delete(visitRecord);
		
	}

	@Override
	public VisitRecord getObject(VisitRecord visitRecord) {
		String hql = "from VisitRecord where id = ";
		hql += visitRecord.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		if(query.list().size()>0){
			return (VisitRecord) query.list().get(0);
		}
		return null;
	}
	
	/**
	 * @param name
	 * @param mobile
	 * @param cardNum
	 * @return
	 */
	public Page getAllVisitRecord(String visitorName,String visitTime,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from VisitRecord "
				+ "where 1=1");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(visitorName)){
			params = ArrayUtils.add(params, "%"+visitorName+"%");
			sbf.append(" and visitorName like ?");
		}
		
		if(!StringUtils.isEmpty(visitTime)){
			params = ArrayUtils.add(params, "%"+visitTime+"%");
			sbf.append(" and visitTime = ?");
		}
		
		sbf.append(" order by visitTime desc,id desc");
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}
	

}
