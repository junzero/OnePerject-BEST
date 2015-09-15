package com.sh.manage.service;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.VisitRecordDao;
import com.sh.manage.entity.VisitRecord;
import com.sh.manage.module.page.Page;

@Service
public class VisitRecordService extends BaseService{
	
	@Autowired
	private VisitRecordDao dao;
	
	public Page findAllVisitRecord(String visitorName, String visitTime,int pageNo, int pageSize) {
		return dao.getAllVisitRecord( visitorName, visitTime, pageNo, pageSize);
	}
	
	public VisitRecord getById(Integer id){
		return dao.get(VisitRecord.class, id);
	}
	
	public void delVisitRecord(VisitRecord visitRecord){
		dao.delete(visitRecord);
	}
	
	public void addVisitRecord(VisitRecord visitRecord){
		visitRecord.setCreatedTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		dao.save(visitRecord);
	}
	
	public void updateVisitRecord(VisitRecord visitRecord){
		VisitRecord old = dao.getObject(visitRecord);
		if(old != null){
			old.setVisitedName(visitRecord.getVisitedName());
			old.setVisitorName(visitRecord.getVisitorName());
			old.setLeaveTime(visitRecord.getLeaveTime());
			old.setVisitTime(visitRecord.getVisitTime());
			old.setReason(visitRecord.getReason());
			old.setMobile(visitRecord.getMobile());
			old.setIdcard(visitRecord.getIdcard());
			dao.update(old);
		}
	}

}
