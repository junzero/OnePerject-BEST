package com.sh.manage.service;


import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.MemberDao;
import com.sh.manage.dao.VipcardDao;
import com.sh.manage.entity.Member;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.module.page.Page;

@Service
public class MemberService extends BaseService{

	@Autowired
	private MemberDao memberDao;
	
	@Autowired 
	private VipcardDao cardDao;
	/**
	 * 查询所有会员
	 * @param pageSize 
	 * @param pageNo 
	 * 
	 * @return
	 */
	public Page findAllAppUser(String name,String mobile,String cardNum,String status,int pageNo, int pageSize) {
		return memberDao.getAllMember(name, mobile, cardNum, status, pageNo, pageSize);
	}
	
	public void addMember(Member member){
		member.setCreated(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") );
		memberDao.save(member);
		if(member.getVipcards()!= null && member.getVipcards().size()>0){
			Vipcard vipcard = cardDao.getObject(member.getVipcards().get(0));
			if(vipcard != null){
				vipcard.setMember(member);
				vipcard.setStatus("1");
				vipcard.setOpenTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
				//TODO 做会员卡是否被绑定校验 
				cardDao.update(vipcard);
			}
			
		}else{
			member.setVipcards(null);
		}
	}
	
	public void updateMember(Member member){
		Member oldMember = memberDao.getObject(member);
		if(oldMember != null){
			oldMember.setAddress(member.getAddress());
			oldMember.setAvatar(member.getAvatar());
			oldMember.setBirthday(member.getBirthday());
			oldMember.setCity(member.getCity());
			oldMember.setEmail(member.getEmail());
			oldMember.setMemberLevel(member.getMemberLevel());
			oldMember.setMobile(member.getMobile());
			oldMember.setName(member.getName());
			oldMember.setPhone(member.getPhone());
			oldMember.setPoint(member.getPoint());
			oldMember.setProvince(member.getProvince());
			oldMember.setSex(member.getSex());
			oldMember.setStatus(member.getStatus());
		}
		List<Vipcard> cards = cardDao.findByMemberId(oldMember.getId());
		for(Vipcard card : cards ){
			card.setStatus("2");
			cardDao.save(card);
		}
		if(member.getVipcards()!= null && member.getVipcards().size()>0){
			Vipcard vipcard = cardDao.getObject(member.getVipcards().get(0));
			if(vipcard != null){
				vipcard.setMember(member);
				vipcard.setStatus("1");
				vipcard.setOpenTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
				//TODO 做会员卡是否被绑定校验 
				cardDao.update(vipcard);
			}
		}else{
			member.setVipcards(null);
		}
		memberDao.update(oldMember);
	}
	
	public Member getMemberById(Integer groupId){
		return  memberDao.get(Member.class, groupId);
	}
	
	public void delMember(Member member){
		memberDao.delete(member);
	}
	
	public List<Member> getUnbindMember(String memberId){
		return memberDao.unbindCard(memberId);
	}
}
