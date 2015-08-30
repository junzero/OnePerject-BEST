package com.sh.manage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.entity.AppUser;
import com.sh.manage.entity.Member;
import com.sh.manage.entity.SysGroup;
import com.sh.manage.module.page.Page;
import com.sh.manage.service.MemberService;
import com.sh.manage.utils.WebUtils;

@Controller
public class MemberController {
	private Logger logger = Logger.getLogger(MemberController.class);
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  
	            ServletRequestDataBinder binder) throws Exception {   
	      DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
	      CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);  
	      binder.registerCustomEditor(Date.class, dateEditor);  
	}
	
	@Autowired
	private MemberService memberService;
	
	/** 当前页 */
	private int initPageNo = 1;

	/** 页面大小 */
	private int pageSize = 5;

	/** Page对象 */
	private Page page;
	
	/**
	 * 跳转会员管理页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/memberManager")
	public ModelAndView appUserManagePage(
			@RequestParam(value = "status", required = false, defaultValue = "") String status,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
			@RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/member/member_manage");
		model.addObject("status", status);
		model.addObject("mobile", mobile);
		model.addObject("cardNum", cardNum);
		model.addObject("name", name);
		//返回的page对象
		page = memberService.findAllAppUser(name, mobile, cardNum, status, pageNo, pageSize);
		// 会员列表
		List<Member> memberList = (List<Member>) page.getList();

		// 翻页带参数
		if(null!=status){
			page.addParam("status",""+status);
		}
		if(null!=mobile){
			page.addParam("mobile",""+mobile);
		}
		if(null != name){
			page.addParam("name",""+name);
		}
		if(null != cardNum){
			page.addParam("cardNum",""+cardNum);
		}
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("memberList", memberList);
		return model;
	} 
	
	/**
	 * 跳转会员新增页面
	 * @return
	 */
	@RequestMapping(value="/toAddMember.do")
    public ModelAndView memberAddPage(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/member/member_add");
		//List<SysGroup> dbGroupList = groupService.getAllGroupList();
		//model.addObject("groupList", dbGroupList);
        return model;
    }
	
	@RequestMapping(value="/toEditMember.do")
    public ModelAndView memberEditPage(@RequestParam(value = "memberId", required = false, defaultValue = "") Integer memberId,HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/member/member_edit");
		model.addObject("member", memberService.getMemberById(memberId));
        return model;
    }
	@RequestMapping(value = "/memberEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> memberEdit(@ModelAttribute Member member,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..会员修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			//  get/new appUser
			memberService.updateMember(member);
			msg="会员修改成功!";
		}catch(Exception e){
			logger.error("controller:会员修改异常!",e);
			msg="会员修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/memberManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:会员修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/memberManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	/**
	 * 当前组织下的会员添加
	 * @return
	 */
	@RequestMapping(value = "/memberAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> memberAdd(@ModelAttribute Member member,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..会员添加...");
		
		
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String msg="";
		
		try{
			memberService.addMember(member);
			msg="会员添加成功!";
		}catch(Exception e){
			logger.error("controller:会员添加异常!",e);
			msg="会员添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/memberManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/memberManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delMember.do")
    public ResponseEntity<String> memberDel(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "memberId", required = false, defaultValue = "") Integer memberId,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {		
		logger.info("controller:GroupController..会员删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			
			/* get sysGroup */
			Member member = memberService.getMemberById(memberId);
			
			
			memberService.delMember(member);

			msg="会员删除成功!";
		}catch(Exception e){
			logger.error("controller:GroupController:会员删除异常!"+memberId,e);
			msg="会员删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/memberManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:GroupController:会员删除结束!");
        return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/memberManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
    }
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page getPage() {
		return page;
	}

	public int getInitPageNo() {
		return initPageNo;
	}

	public void setInitPageNo(int initPageNo) {
		this.initPageNo = initPageNo;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
