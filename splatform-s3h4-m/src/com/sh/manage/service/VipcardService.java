package com.sh.manage.service;

import java.util.Date;
import java.util.List;

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
		if(vipcard.getMember() != null){
			if(vipcard.getMember().getId() == null){
				vipcard.setMember(null);
			}else{
				//TODO 做会员卡 是否被绑定判断 有被其他会员绑定 抛出异常
			}
		}
		
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
		Vipcard oldCard = cardDao.getObject(vipcard);
		if(oldCard != null){
			oldCard.setBalance(vipcard.getBalance());
			oldCard.setCardNum(vipcard.getCardNum());
			oldCard.setDeadline(vipcard.getDeadline());
			oldCard.setOpenTime(vipcard.getOpenTime());
			oldCard.setStatus(vipcard.getStatus());
			oldCard.setMember(vipcard.getMember());
			oldCard.setPassword(vipcard.getPassword());
			oldCard.setType(vipcard.getType());
		}
		if(vipcard.getMember() != null){
			if(vipcard.getMember().getId() == null){
				oldCard.setMember(null);
			}else{
				//TODO 做会员卡 是否被绑定判断 有被其他会员绑定 抛出异常

			}
		}
		cardDao.update(oldCard);
	}
	
	public List<Vipcard> getUnbindCard(String memberId){
		return cardDao.unbind(memberId);
	}
	
	public List<Vipcard> findByFilter(String filter){
		return cardDao.findByFilter(filter);
	}
}
