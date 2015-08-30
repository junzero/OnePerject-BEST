package com.sh.manage.service;


import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.MemberDao;
import com.sh.manage.entity.Member;
import com.sh.manage.exception.SPlatformServiceException;
import com.sh.manage.module.page.Page;

@Service
public class MemberService extends BaseService{

	private Logger logger = Logger.getLogger(MemberService.class);
	
	@Autowired
	private MemberDao memberDao;
	
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
		member.setCreated(new Date());
		memberDao.save(member);
	}
	
	public void updateMember(Member member){
		Member oldMember = memberDao.getObject(member);
		if(oldMember != null){
			oldMember.setAddress(member.getAddress());
			oldMember.setAvatar(member.getAvatar());
			oldMember.setBalance(member.getBalance());
			oldMember.setBirthday(member.getBirthday());
			oldMember.setCardCreated(member.getCardCreated());
			oldMember.setCardDeadline(member.getCardDeadline());
			oldMember.setCardNum(member.getCardNum());
			oldMember.setCardPassword(member.getCardPassword());
			oldMember.setCity(member.getCity());
			oldMember.setCreateUser(member.getCreateUser());
			oldMember.setEmail(member.getEmail());
			oldMember.setGroupId(member.getGroupId());
			oldMember.setMemberLevel(member.getMemberLevel());
			oldMember.setMobile(member.getMobile());
			oldMember.setName(member.getName());
			oldMember.setPhone(member.getPhone());
			oldMember.setPoint(member.getPoint());
			oldMember.setProvince(member.getProvince());
			oldMember.setSex(member.getSex());
			oldMember.setStatus(member.getStatus());
		}
		memberDao.update(oldMember);
	}
	
	public Member getMemberById(Integer groupId){
		return  memberDao.get(Member.class, groupId);
	}
	
	public void delMember(Member member){
		memberDao.delete(member);
	}
}
