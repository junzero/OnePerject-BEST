package com.sh.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
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

import com.sh.manage.entity.VisitRecord;
import com.sh.manage.module.page.Page;
import com.sh.manage.service.VisitRecordService;
import com.sh.manage.utils.WebUtils;

@Controller
public class VisitRecordController {
	
private Logger logger = Logger.getLogger(VisitRecordController.class);
	
	@Autowired
	private VisitRecordService visitRecordService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/visitRecordManager")
	public ModelAndView visitRecordManagePage(
			@RequestParam(value = "visitorName", required = false, defaultValue = "") String visitorName,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/visitRecord/visitRecord_manage");
		model.addObject("visitorName", visitorName);
		model.addObject("startDate", startDate);
		model.addObject("endDate", endDate);
		// 会员列表
		page = visitRecordService.findAllVisitRecord(visitorName, startDate, endDate, pageNo, pageSize);
		List<VisitRecord> visitRecordList = (List<VisitRecord>) page.getList();

		// 翻页带参数
		if(null!=visitorName){
			page.addParam("visitorName",""+visitorName);
		}
		if(null != startDate){
			page.addParam("startDate",""+startDate);
		}
			
		if(null != startDate){
			page.addParam("endDate",""+endDate);
		}
			
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("visitRecordList", visitRecordList);
		return model;
	} 

	
	@RequestMapping(value="/toAddVisitRecord.do")
    public ModelAndView toAddVisitRecord(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/visitRecord/visitRecord_add");
        return model;
    }
	
	/**
	 * 当前组织下的会员添加
	 * @return
	 */
	@RequestMapping(value = "/visitRecordAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> visitRecordAdd(@ModelAttribute VisitRecord visitRecord,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..访问记录添加...");
		
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String msg="";
		
		try{
			visitRecord.setCreatedUser(request.getSession().getAttribute("name").toString());
			visitRecordService.addVisitRecord(visitRecord);
			msg="访问记录添加成功!";
		}catch(Exception e){
			logger.error("controller:访问记录添加异常!",e);
			msg="访问记录添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/visitRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/visitRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/toEditVisitRecord.do")
    public ModelAndView toEditVisitRecord(@RequestParam(value = "id", required = true) Integer id,HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/visitRecord/visitRecord_edit");
		model.addObject("visitRecord", visitRecordService.getById(id));
        return model;
    }
	
	@RequestMapping(value = "/visitRecordEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> visitRecordEdit(@ModelAttribute VisitRecord visitRecord,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..访问记录修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			//  get/new appUser
			visitRecordService.updateVisitRecord(visitRecord);
			msg="访问记录修改成功!";
		}catch(Exception e){
			logger.error("controller:访问记录修改异常!",e);
			msg="访问记录修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/visitRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:访问记录修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/visitRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delVisitRecord.do")
    public ResponseEntity<String> delVisitRecord(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {		
		logger.info("controller:GroupController..访问记录删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try{
			VisitRecord visitRecord = visitRecordService.getById(id);
			visitRecordService.delVisitRecord(visitRecord);
			msg="访问记录删除成功!";
		}catch(Exception e){
			logger.error("controller:GroupController:会员卡删除异常!"+id,e);
			msg="访问记录删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/visitRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:GroupController:访问记录删除结束!");
        return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/visitRecordManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="exportExcel")
	public String exportExcel(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			HttpServletResponse response) {
		response.setContentType("application/binary;charset=ISO8859_1");
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			String fileName = new String(("来访记录登记表").getBytes(), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			visitRecordService.exportExcel(startDate, endDate, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
