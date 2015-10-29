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
import com.sh.manage.module.page.Page;
import com.sh.manage.service.BuyRecordService;
import com.sh.manage.utils.WebUtils;

@Controller
public class BuyRecordController {
	
	private Logger logger = Logger.getLogger(BuyRecordController.class);
	
	@Autowired
	private BuyRecordService buyRecordService;
	
	@RequestMapping(value = "/recordDetail")
	public ModelAndView recordDetail(@RequestParam(value = "recordId", required = true) Integer recordId){
		ModelAndView model = new ModelAndView("/buyRecord/recordDetail");
		model.addObject("record", buyRecordService.getById(recordId));
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/memberBuyRecords")
	public ModelAndView memberBuyRecords(
			@RequestParam(value = "memberId", required = true) Integer memberId,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/buyRecord/memberBuyRecord");
		model.addObject("memberId", memberId);
		// 会员列表
		page = buyRecordService.findAllByMemberId(memberId, pageNo, pageSize);
		List<BuyRecord> buyRecords = (List<BuyRecord>) page.getList();

		// 翻页带参数
		
		if(null != memberId){
			page.addParam("memberId",""+memberId);
		}
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("buyRecords", buyRecords);
		return model;
	} 
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/buyRecordManager")
	public ModelAndView buyRecordManagePage(
			@RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/buyRecord/buyRecord_manage");
		model.addObject("cardNum", cardNum);
		// 会员列表
		page = buyRecordService.findAllBuyRecord(cardNum, pageNo, pageSize);
		List<BuyRecord> buyRecords = (List<BuyRecord>) page.getList();

		// 翻页带参数
		
		if(null != cardNum){
			page.addParam("cardNum",""+cardNum);
		}
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("buyRecords", buyRecords);
		return model;
	} 
	
	@RequestMapping(value="/toAddBuyRecord.do")
    public ModelAndView toAddGoods(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/buyRecord/buyRecord_add");
        return model;
    }
	
	/**
	 * 当前组织下的会员添加
	 * @return
	 */
	@RequestMapping(value = "/buyRecordAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> goodsAdd(@ModelAttribute BuyRecord buyRecord,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..消费记录添加...");
		
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String msg="";
		
		try{
			buyRecord.setCreateUser(request.getSession().getAttribute("name").toString());
			buyRecordService.addBuyRecord(buyRecord);
			msg="消费记录添加成功!";
		}catch(Exception e){
			logger.error("controller:消费记录添加异常!",e);
			msg = e.getMessage();
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/buyRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/buyRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/toEditBuyRecord.do")
    public ModelAndView toEditBuyRecord(@RequestParam(value = "id", required = true) Integer id,HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/buyRecord/buyRecord_edit");
		model.addObject("buyRecord", buyRecordService.getById(id));
        return model;
    }
	
	@RequestMapping(value = "/buyRecordEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> buyRecordEdit(@ModelAttribute BuyRecord buyRecord,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..消费记录修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			//  get/new appUser
			buyRecordService.updateBuyRecord(buyRecord);
			msg="消费记录修改成功!";
		}catch(Exception e){
			logger.error("controller:消费记录修改异常!",e);
			msg=e.getMessage();
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/buyRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:商品修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/buyRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delBuyRecord.do")
    public ResponseEntity<String> delBuyRecord(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {		
		logger.info("controller:GroupController..消费记录删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try{
			BuyRecord buyRecord = buyRecordService.getById(id);
			buyRecordService.delBuyRecord(buyRecord);
			msg="消费记录删除成功!";
		}catch(Exception e){
			logger.error("controller:GroupController:会员卡删除异常!"+id,e);
			msg="消费记录删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/buyRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:GroupController:商品删除结束!");
        return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/buyRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
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
