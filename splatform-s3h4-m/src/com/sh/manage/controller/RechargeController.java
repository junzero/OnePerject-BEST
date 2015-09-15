package com.sh.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.entity.BuyRecord;
import com.sh.manage.entity.Recharge;
import com.sh.manage.module.page.Page;
import com.sh.manage.service.BuyRecordService;
import com.sh.manage.service.RechargeService;
import com.sh.manage.utils.WebUtils;

@Controller
public class RechargeController {
	private Logger logger = Logger.getLogger(RechargeController.class);
	
	@Autowired
	private RechargeService rechargeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rechargeManager")
	public ModelAndView goodsManagePage(
			@RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
			@RequestParam(value = "memberName", required = false, defaultValue = "") String memberName,
			@RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/recharge/recharge_manage");
		model.addObject("cardNum", cardNum);
		model.addObject("memberName", memberName);
		model.addObject("mobile", mobile);
		// 会员列表
		page = rechargeService.findAllRecharge(cardNum, mobile, memberName, pageNo, pageSize);
		List<Recharge> rechargeList = (List<Recharge>) page.getList();

		// 翻页带参数
		
		if(null != cardNum){
			page.addParam("cardNum",""+cardNum);
			page.addParam("mobile",""+cardNum);
			page.addParam("memberName",""+cardNum);
		}
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("rechargeList", rechargeList);
		return model;
	} 
	
	@RequestMapping(value="/toAddRecharge.do")
    public ModelAndView toAddGoods(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/recharge/recharge_add");
        return model;
    }
	
	@RequestMapping(value = "/rechargeAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> goodsAdd(@ModelAttribute Recharge recharge,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..充值记录添加...");
		
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String msg="";
		
		try{
			recharge.setCreatedUser(request.getSession().getAttribute("name").toString());
			rechargeService.addRecharge(recharge);
			msg="充值记录添加成功!";
		}catch(Exception e){
			logger.error("controller:充值记录添加异常!",e);
			msg = e.getMessage();
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/rechargeManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/rechargeManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/toEditRecharge.do")
    public ModelAndView toEditRecharge(@RequestParam(value = "id", required = true) Integer id,HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/recharge/recharge_edit");
		model.addObject("recharge", rechargeService.getById(id));
        return model;
    }
	
	@RequestMapping(value = "/rechargeEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> rechargeEdit(@ModelAttribute Recharge recharge,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..充值记录修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			//  get/new appUser
			rechargeService.updateRecharge(recharge);
			msg="充值记录修改成功!";
		}catch(Exception e){
			logger.error("controller:充值记录修改异常!",e);
			msg=e.getMessage();
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/rechargeManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:充值记录结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/rechargeManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delRecharge.do")
    public ResponseEntity<String> delRecharge(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {		
		logger.info("controller:GroupController..充值记录删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try{
			Recharge recharge = rechargeService.getById(id);
			rechargeService.delRecharge(recharge);
			msg="充值记录删除成功!";
		}catch(Exception e){
			logger.error("controller:GroupController:会员卡删除异常!"+id,e);
			msg=e.getMessage();
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/rechargeManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:GroupController:充值记录删除结束!");
        return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/rechargeManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
    }
	
	/** 当前页 */
	private int initPageNo = 1;

	/** 页面大小 */
	private int pageSize = 5;

	/** Page对象 */
	private Page page;
	
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
