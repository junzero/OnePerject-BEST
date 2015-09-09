package com.sh.manage.service;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.VipcardDao;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.module.page.Page;

@Service
public class VipcardService extends BaseService{
	
	@Autowired
	private VipcardDao cardDao;

	/**
	 * 查询所有会员
	 * @param pageSize 
	 * @param pageNo 
	 * 
	 * @return
	 */
	public Page findAllVipcard(String cardNum, String status,int pageNo, int pageSize) {
		return cardDao.getAllCard( cardNum, status, pageNo, pageSize);
	}
	
	public void addVipcard(Vipcard vipcard){
		vipcard.setCreatedTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") );
		cardDao.save(vipcard);
	}
	
	public void delVipcard(Vipcard vipcard){
		cardDao.delete(vipcard);
	}
	
	public Vipcard getByCardNum(String cardNum){
		Vipcard vipcard = new Vipcard();
		vipcard.setCardNum(cardNum);
		return cardDao.getObject(vipcard);
	}
	
	public void updateVipcard(Vipcard vipcard){
		
	}
	
}
