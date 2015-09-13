package com.sh.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.BuyRecordDao;
import com.sh.manage.entity.BuyRecord;
import com.sh.manage.module.page.Page;

@Service
public class BuyRecordService extends BaseService{
	
	@Autowired
	private BuyRecordDao buyRecordDao;
	
	public Page findAllBuyRecord(String cardNum, int pageNo, int pageSize) {
		return buyRecordDao.getAllBuyRecord( cardNum, pageNo, pageSize);
	}
	
	public BuyRecord getById(Integer id){
		return buyRecordDao.get(BuyRecord.class, id);
	}
	
	public void delBuyRecord(BuyRecord buyRecord){
		buyRecordDao.delete(buyRecord);
	}
	
	public void addBuyRecord(BuyRecord buyRecord){
		buyRecordDao.save(buyRecord);
	}
	
	public void updateBuyRecord(BuyRecord buyRecord){
		
	}
}
