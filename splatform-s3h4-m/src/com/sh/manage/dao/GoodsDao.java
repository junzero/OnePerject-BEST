package com.sh.manage.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.Goods;
import com.sh.manage.module.page.Page;

@Repository
public class GoodsDao extends AbstractBaseDao<Goods>{

	@Override
	public Integer addObject(Goods goods) {
		return (Integer) this.getCurrentSession().save(goods);
	}

	@Override
	public void updateObject(Goods goods) {
		this.getCurrentSession().save(goods);
		
	}

	@Override
	public void deleteObject(Goods goods) {
		this.getCurrentSession().delete(goods);
	}

	@Override
	public Goods getObject(Goods goods) {
		String hql = "from Goods where id= ";
		hql += goods.getId();
		Query query = this.getCurrentSession().createQuery(hql);
		if(query.list().size()>0){
			return (Goods) query.list().get(0);
		}
		return null;
	}
	
	/**
	 * 获取所有会员卡
	 * @param name
	 * @param mobile
	 * @param cardNum
	 * @return
	 */
	public Page getAllCard(String name,String type,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from Goods "
				+ "where 1=1");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(name)){
			params = ArrayUtils.add(params, "%"+name+"%");
			sbf.append(" and name like ?");
		}
		
		if(!StringUtils.isEmpty(type)){
			params = ArrayUtils.add(params, type);
			sbf.append(" and type = ?");
		}
		sbf.append(" order by id");
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}

	public List<Goods> findAll(String name){
		name += "%"+name+"%";
		return this.queryhqlList("from  Goods where name like ?", new Object[]{name});
	}
}
