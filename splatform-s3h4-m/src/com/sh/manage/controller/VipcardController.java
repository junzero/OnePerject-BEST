package com.sh.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.entity.Member;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.module.page.Page;
import com.sh.manage.service.VipcardService;
import com.sh.manage.utils.JsonUtils;
import com.sh.manage.utils.WebUtils;

@Controller
public class VipcardController {
	
	private Logger logger = Logger.getLogger(VipcardController.class);
	
	@Autowired
	private VipcardService vipcardService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/vipcardManager")
	public ModelAndView appUserManagePage(
			@RequestParam(value = "status", required = false, defaultValue = "") String status,
			@RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/vipcard/vipcard_manage");
		model.addObject("status", status);
		model.addObject("cardNum", cardNum);
		// 会员列表
		page = vipcardService.findAllVipcard(cardNum, status, pageNo, pageSize);
		List<Vipcard> vipcardList = (List<Vipcard>) page.getList();

		// 翻页带参数
		if(null!=status){
			page.addParam("status",""+status);
		}
		if(null != cardNum){
			page.addParam("cardNum",""+cardNum);
		}
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("vipcardList", vipcardList);
		return model;
	} 
	
	@RequestMapping(value="/unbindCards.do")
	@ResponseBody
	public List<Vipcard> unbindCards(HttpServletRequest req){
		List<Vipcard> cards = vipcardService.getUnbindCard(req.getParameter("memberId"));
		return cards;
	}
	
	@RequestMapping(value="/toAddVipcard.do")
    public ModelAndView memberAddPage(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/vipcard/vipcard_add");
        return model;
    }

	@RequestMapping(value="/validCardNum.do")
	@ResponseBody
	public Map<String,String> validCardNum(HttpServletRequest req){
		Map<String,String> result = new HashMap<String,String>();
		Vipcard vipcard = vipcardService.getByCardNum(req.getParameter("param"));
		String id = req.getParameter("id");
		if(vipcard == null){
			result.put("status", "y");
			result.put("info", "");
		}else{
			if(id != null){
				if(Integer.valueOf(id).equals(vipcard.getId())){
					result.put("status", "y");
					result.put("info", "");
				}else{
					result.put("status", "n");
					result.put("info", "该卡号已存在");
				}
			}else{
				result.put("status", "n");
				result.put("info", "该卡号已存在");
			}
		}
		return result;
	}
	
	/**
	 * 当前组织下的会员添加
	 * @return
	 */
	@RequestMapping(value = "/vipcardAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> vipcardsAdd(@ModelAttribute Vipcard vipcard,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..会员添加...");
		
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String msg="";
		
		try{
			vipcardService.addVipcard(vipcard);
			msg="会员卡添加成功!";
		}catch(Exception e){
			logger.error("controller:会员卡添加异常!",e);
			msg="会员卡添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/vipcardManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/vipcardManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/toEditVipcard.do")
    public ModelAndView vipcardEditPage(@RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/vipcard/vipcard_edit");
		model.addObject("vipcard", vipcardService.getByCardNum(cardNum));
        return model;
    }
	
	@RequestMapping(value = "/vipcardEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> vipcardEdit(@ModelAttribute Vipcard vipcard,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..会员卡修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			//  get/new appUser
			vipcardService.updateVipcard(vipcard);
			msg="会员卡修改成功!";
		}catch(Exception e){
			logger.error("controller:会员卡修改异常!",e);
			msg="会员卡修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/vipcardManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:会员修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/vipcardManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delVipcard.do")
    public ResponseEntity<String> vipcardDel(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {		
		logger.info("controller:GroupController..会员卡删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try{
			Vipcard vipcard = vipcardService.getByCardNum(cardNum);
			vipcardService.delVipcard(vipcard);
			msg="会员卡删除成功!";
		}catch(Exception e){
			logger.error("controller:GroupController:会员卡删除异常!"+cardNum,e);
			msg="会员卡删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/vipcardManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:GroupController:会员卡删除结束!");
        return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/vipcardManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
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
