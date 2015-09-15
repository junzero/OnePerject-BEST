package com.sh.manage.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.MemberDao;
import com.sh.manage.dao.RechargeDao;
import com.sh.manage.dao.VipcardDao;
import com.sh.manage.entity.Member;
import com.sh.manage.entity.Recharge;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.exception.SPlatformServiceException;
import com.sh.manage.module.page.Page;

@Service
public class RechargeService extends BaseService{
	@Autowired
	private RechargeDao dao;
	
	@Autowired
	private VipcardDao cardDao;
	
	@Autowired
	private MemberDao memberDao;
	
	
	public Page findAllRecharge(String cardNum,String mobile,String memberName,int pageNo, int pageSize){
		return dao.getAllRecharge( cardNum,mobile,memberName, pageNo, pageSize);
	}
	
	public Recharge getById(Integer id){
		return dao.get(Recharge.class, id);
	}
	
	public void delRecharge(Recharge recharge) throws SPlatformServiceException{
		Vipcard card = new Vipcard();
		card.setCardNum(recharge.getCardNum());
		card = cardDao.getObject(card);
		Member member = card.getMember();
		
		member.setPoint(member.getPoint()-recharge.getPoint());
		card.setBalance(card.getBalance().subtract(recharge.getMoney()));
		if(card.getBalance().compareTo(new BigDecimal(0)) < 0){
			throw new SPlatformServiceException("该会员卡余额不足，不能删除该充值记录");
		}
		cardDao.save(card);
		memberDao.save(member);
		dao.delete(recharge);
	}
	
	public void addRecharge(Recharge recharge){
		Vipcard card = new Vipcard();
		card.setCardNum(recharge.getCardNum());
		card = cardDao.getObject(card);
		Member member = card.getMember();
		
		recharge.setCreatedDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		recharge.setMemberId(member.getId());
		recharge.setMobile(member.getMobile());
		recharge.setMemberName(member.getName());
		recharge.setPoint(recharge.getMoney().intValue());//TODO 计算获得积分
		
		member.setPoint(member.getPoint()+recharge.getPoint());
		card.setBalance(card.getBalance().add(recharge.getMoney()));
		memberDao.save(member);
		dao.save(recharge);
	}
	
	public void updateRecharge(Recharge recharge) throws SPlatformServiceException{
		Vipcard card = new Vipcard();
		card.setCardNum(recharge.getCardNum());
		card = cardDao.getObject(card);
		Member member = card.getMember();
		
		Recharge oldRecharge = dao.getObject(recharge);
		if(!oldRecharge.getCardNum().equals(card.getCardNum())){
			Vipcard oldCard = new Vipcard();
			oldCard.setCardNum(oldRecharge.getCardNum());
			oldCard = cardDao.getObject(oldCard);
			Member oldMember = oldCard.getMember();
			
			oldCard.setBalance(oldCard.getBalance().subtract(oldRecharge.getMoney()));
			oldMember.setPoint(oldMember.getPoint()-oldRecharge.getPoint());
			
			if(oldCard.getBalance().compareTo(new BigDecimal(0)) < 0){
				throw new SPlatformServiceException("旧会员卡余额不足，不能修改该充值记录");
			}
			cardDao.save(oldCard);
			memberDao.save(oldMember);
			
			card.setBalance(card.getBalance().add(recharge.getMoney()));
			member.setPoint(member.getPoint()+recharge.getMoney().intValue());
		}else{
			card.setBalance(card.getBalance().subtract(oldRecharge.getMoney()).add(recharge.getMoney()));
			member.setPoint(member.getPoint()-oldRecharge.getPoint()+recharge.getMoney().intValue());
		}
		
		if(card.getBalance().compareTo(new BigDecimal(0)) < 0){
			throw new SPlatformServiceException("该会员卡余额不足，不能修改该充值记录");
		}
		cardDao.save(card);
		memberDao.save(member);
		oldRecharge.setCardNum(card.getCardNum());
		oldRecharge.setMemberId(member.getId());
		oldRecharge.setMobile(member.getMobile());
		oldRecharge.setMemberName(member.getName());
		oldRecharge.setMoney(recharge.getMoney());
		oldRecharge.setPoint(recharge.getMoney().intValue());
		dao.save(oldRecharge);
	}
}	
