package com.sh.manage.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.BuyRecordDao;
import com.sh.manage.dao.GoodsDao;
import com.sh.manage.dao.MemberDao;
import com.sh.manage.dao.VipcardDao;
import com.sh.manage.entity.BuyRecord;
import com.sh.manage.entity.BuyRecordItem;
import com.sh.manage.entity.Goods;
import com.sh.manage.entity.Member;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.exception.SPlatformServiceException;
import com.sh.manage.module.page.Page;

@Service
public class BuyRecordService extends BaseService{
	
	@Autowired
	private BuyRecordDao buyRecordDao;
	
	@Autowired
	private VipcardDao cardDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	public Page findAllBuyRecord(String cardNum, int pageNo, int pageSize) {
		return buyRecordDao.getAllBuyRecord( cardNum, pageNo, pageSize);
	}
	
	public BuyRecord getById(Integer id){
		return buyRecordDao.get(BuyRecord.class, id);
	}
	
	public void delBuyRecord(BuyRecord buyRecord){
		Vipcard card = new Vipcard();
		card.setCardNum(buyRecord.getCardNum());
		card = cardDao.getObject(card);
		Member member = card.getMember();
		Integer point = member.getPoint()-buyRecord.getPoint();
		member.setPoint(point);
		BigDecimal balance = card.getBalance().add(buyRecord.getTotalPrice());
		card.setBalance(balance);
		buyRecordDao.delete(buyRecord);
		cardDao.save(card);
		memberDao.save(member);
	}
	
	public void addBuyRecord(BuyRecord buyRecord) throws SPlatformServiceException{
		List<BuyRecordItem> items = buyRecord.getItems();
		Vipcard card = new Vipcard();
		card.setCardNum(buyRecord.getCardNum());
		card = cardDao.getObject(card);
		BigDecimal totalPrice = new BigDecimal(0);
		for (BuyRecordItem item : items) {
			Goods goods = goodsDao.get(Goods.class, item.getGoodsId());
			item.setGoodsName(goods.getName());
			item.setPirce(goods.getPrice());
			item.setTotalPrice(item.getPirce().multiply(new BigDecimal(item.getQuantity())));
			totalPrice = totalPrice.add(item.getTotalPrice());
		}
		if(card.getBalance().compareTo(totalPrice) < 0){
			throw new SPlatformServiceException("会员卡余额不足，无法添加消费记录");
		}
		card.setBalance(card.getBalance().subtract(totalPrice));
		buyRecord.setCreatedDate(new Date(System.currentTimeMillis()));
		buyRecord.setBuyTime(new Date(System.currentTimeMillis()));
		buyRecord.setMemberId(card.getMember().getId());
		buyRecord.setMemberName(card.getMember().getName());
		buyRecord.setTotalPrice(totalPrice);
		buyRecord.setPoint(totalPrice.intValue());//TODO 计算获得积分
		buyRecordDao.save(buyRecord);
		Member member = card.getMember();
		Integer point = member.getPoint()+buyRecord.getPoint();
		card.getMember().setPoint(point);
		cardDao.save(card);
		memberDao.save(member);
	}
	
	public void updateBuyRecord(BuyRecord buyRecord) throws SPlatformServiceException{
		List<BuyRecordItem> items = buyRecord.getItems();
		Vipcard card = new Vipcard();
		card.setCardNum(buyRecord.getCardNum());
		card = cardDao.getObject(card);
		BigDecimal totalPrice = new BigDecimal(0);
		for (BuyRecordItem item : items) {
			Goods goods = goodsDao.get(Goods.class, item.getGoodsId());
			item.setGoodsName(goods.getName());
			item.setPirce(goods.getPrice());
			item.setTotalPrice(item.getPirce().multiply(new BigDecimal(item.getQuantity())));
			totalPrice = totalPrice.add(item.getTotalPrice());
		}
		
		BuyRecord old = buyRecordDao.getObject(buyRecord);
		Member member = card.getMember();
		if(!old.getCardNum().equals(card.getCardNum())){
			Vipcard oldCard = new Vipcard();
			oldCard.setCardNum(old.getCardNum());
			oldCard = cardDao.getObject(oldCard);
			BigDecimal oldBalance = oldCard.getBalance().add(old.getTotalPrice());
			oldCard.setBalance(oldBalance);
			Member oldMember = oldCard.getMember();
			Integer oldPoint = oldMember.getPoint()-old.getTotalPrice().intValue();
			oldMember.setPoint(oldPoint);
			
			cardDao.save(oldCard);
			memberDao.save(oldMember);
			
			member.setPoint(member.getPoint()+totalPrice.intValue());
			card.setBalance(card.getBalance().subtract(totalPrice));
			
		}else{
			
			Integer point = member.getPoint() - old.getPoint() + totalPrice.intValue();
			member.setPoint(point);
			card.setBalance(card.getBalance().add(old.getTotalPrice()).subtract(totalPrice));
		}
		
		if (old.getTotalPrice().add(card.getBalance()).compareTo(totalPrice) < 0) {
			throw new SPlatformServiceException("会员卡余额不足，无法添加消费记录");
		}
		
		cardDao.save(card);
		memberDao.save(member);
		old.setCardNum(card.getCardNum());
		old.setRemark(buyRecord.getRemark());
		old.setMemberId(card.getMember().getId());
		old.setMemberName(card.getMember().getName());
		old.setTotalPrice(totalPrice);
		old.setPoint(totalPrice.intValue());
		old.setItems(items);
		buyRecordDao.save(old);
	}
	
	public Page findAllByMemberId(Integer memberId, int pageNo, int pageSizes){
		 return buyRecordDao.getAllByMemberId(memberId, pageNo, pageSizes);
	}
}
