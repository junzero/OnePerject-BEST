package com.sh.manage.dao;

import java.util.List;

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
		String hql = "from Vipcard where cardNum= ";
		hql += "'"+vipcard.getCardNum()+"'";
		Query query = this.getCurrentSession().createQuery(hql);
		if(query.list().size()>0){
			return (Vipcard) query.list().get(0);
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
	public Page getAllCard(String cardNum,String status,int pageNo, int pageSize){
		StringBuffer sbf = new StringBuffer();
		sbf.append("from Vipcard "
				+ "where 1=1");
		
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(cardNum)){
			params = ArrayUtils.add(params, "%"+cardNum+"%");
			sbf.append(" and cardNum like ?");
		}
		
		if(!StringUtils.isEmpty(status)){
			params = ArrayUtils.add(params, status);
			sbf.append(" and status = ?");
		}
		sbf.append(" order by id");
		return this.queryList(sbf.toString(), params, pageNo, pageSize);
	}
	
	
	public List<Vipcard> unbind(String memberId){
		String sql = "select v.* from t_vipcard v where v.member_id is null ";
		if(!StringUtils.isEmpty(memberId)){
			sql += " or v.member_id = ?";
			return (List<Vipcard>) this.queryModelSqlList(sql, new Object[]{Integer.valueOf(memberId)}, Vipcard.class);
		}
		return (List<Vipcard>) this.queryModelSqlList(sql, null, Vipcard.class);
	}
	
	public List<Vipcard> findByMemberId(Integer memberId){
		return this.queryhqlList("from  Vipcard v where v.member.id = ?", new Object[]{memberId});
	}
	
	public List<Vipcard> findByFilter(String filter){
		StringBuffer hql = new StringBuffer("from Vipcard where status='1'");
		Object[] params = new Object[]{};
		if(!StringUtils.isEmpty(filter)){
			params = ArrayUtils.add(params, "%"+filter+"%");
			hql.append(" and (cardNum like ?");
			params = ArrayUtils.add(params, "%"+filter+"%");
			hql.append(" or member.name like ?");
			params = ArrayUtils.add(params, "%"+filter+"%");
			hql.append(" or member.mobile like ?)");
		}
		return this.queryhqlList(hql.toString(), params);
		
	}
}
