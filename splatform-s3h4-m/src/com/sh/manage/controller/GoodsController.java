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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.entity.Goods;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.module.page.Page;
import com.sh.manage.service.GoodsService;
import com.sh.manage.utils.WebUtils;

@Controller
public class GoodsController {
	private Logger logger = Logger.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsService goodsService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/goodsManager")
	public ModelAndView goodsManagePage(
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/goods/goods_manage");
		model.addObject("type", type);
		model.addObject("name", name);
		// 会员列表
		page = goodsService.findAllGoods(name, type, pageNo, pageSize);
		List<Goods> goodsList = (List<Goods>) page.getList();

		// 翻页带参数
		if(null!=type){
			page.addParam("type",""+type);
		}
		if(null != name){
			page.addParam("name",""+name);
		}
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("goodsList", goodsList);
		return model;
	} 
	
	@RequestMapping(value="/allGoods.do")
	@ResponseBody
	public List<Goods> allGppds(HttpServletRequest req){
		return goodsService.findAll();
	}
	
	@RequestMapping(value="/toAddGoods.do")
    public ModelAndView toAddGoods(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/goods/goods_add");
        return model;
    }
	
	/**
	 * 当前组织下的会员添加
	 * @return
	 */
	@RequestMapping(value = "/goodsAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> goodsAdd(@ModelAttribute Goods goods,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..商品添加...");
		
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String msg="";
		
		try{
			goodsService.addGoods(goods);
			msg="商品添加成功!";
		}catch(Exception e){
			logger.error("controller:商品添加异常!",e);
			msg="商品添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/goodsManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/goodsManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/toEditGoods.do")
    public ModelAndView toEditGoods(@RequestParam(value = "id", required = true) Integer id,HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/goods/goods_edit");
		model.addObject("goods", goodsService.getById(id));
        return model;
    }
	
	@RequestMapping(value = "/goodsEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> goodsEdit(@ModelAttribute Goods goods,HttpServletRequest request,HttpServletResponse response,
			Model model){
		logger.info("controller:..商品修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			//  get/new appUser
			goodsService.updateGoods(goods);
			msg="商品修改成功!";
		}catch(Exception e){
			logger.error("controller:商品修改异常!",e);
			msg="商品修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/goodsManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:商品修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/goodsManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delGoods.do")
    public ResponseEntity<String> delGoods(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {		
		logger.info("controller:GroupController..商品删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try{
			Goods goods = goodsService.getById(id);
			goodsService.delGoods(goods);
			msg="商品删除成功!";
		}catch(Exception e){
			logger.error("controller:GroupController:会员卡删除异常!"+id,e);
			msg="商品删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/goodsManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:GroupController:商品删除结束!");
        return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/goodsManager.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
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
