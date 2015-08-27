package com.sh.manage.controller;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.constants.SessionConstants;
import com.sh.manage.entity.SysMenu;
import com.sh.manage.entity.SysOperate;
import com.sh.manage.entity.SysRole;
import com.sh.manage.exception.SPlatformServiceException;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.LoginUser;
import com.sh.manage.service.OperateService;
import com.sh.manage.service.RoleService;
import com.sh.manage.service.UserService;
import com.sh.manage.utils.JsonUtils;
import com.sh.manage.utils.ResponseUtils;
import com.sh.manage.utils.TimeUtil;
import com.sh.manage.utils.WebUtils;

/**
 * 权限管理
 * @author
 * 
 */

@Controller
public class RoleController {
	
	private Logger logger = Logger.getLogger(RoleController.class);


	@Autowired
	private UserService userService;
	
	/**
	 * 注入roleSerice包装
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * 注入operateService包装
	 */
	@Autowired
	private OperateService operateService;
	
	/** 当前页 */
	private int initPageNo = 1;

	/** 页面大小 */
	private int pageSize = 5;

	/** Page对象 */
	private Page page;
	
	/**
	 * 所有菜单数据串
	 * 格式：{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
	 */
	private String menuStrs = "";
	
	/**
	 * 是否展开
	 */
	private int expand;
	
	
	public int getExpand() {
		return expand;
	}

	public void setExpand(int expand) {
		this.expand = expand;
	}
	/**
	 * 跳转组织管理页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/romanage.do")
    public ModelAndView roleManagePage(HttpServletRequest req,
			HttpServletResponse resp,
    		@RequestParam(value = "gIndex", required = false, defaultValue = "") Integer gIndex,
    		@RequestParam(value = "roleName", required = false, defaultValue = "") String roleName,
    		@RequestParam(value = "expand", required = false, defaultValue = "false") Boolean expand,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		
		//HttpSession session = req.getSession();
		//获取角色列表
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		ModelAndView model = new ModelAndView("/role/role_manage");
		/**获取组列表*/
		
		page = roleService.getRoles(roleName,pageNo, pageSize);
		List<SysRole> roleList = (List<SysRole>) page.getList();//
		
		

		
		expand = true;
		model.addObject("expand",expand);

		model.addObject("roleList", roleList);
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("roleName", roleName);
		logger.info("..expand:"+expand);
        return model;
    }
	
	
	/**
	 * 跳转角色添加页面
	 */
	@RequestMapping(value="/addRole.do")
    public ModelAndView roleAddPage(HttpServletRequest req,
			HttpServletResponse resp) {
		HttpSession session = req.getSession();
		ModelAndView model = new ModelAndView("/role/role_add");
		
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
		if (null != _loginUser) {
			//获取菜单列表
			menuStrs = (String) session.getAttribute("menuStrs");
			model.addObject("menuStrs", menuStrs);
			logger.info("menuStrs:"+menuStrs);
		}
        return model;
    }
	
	
	/***
	 * 角色新增
	 * @param request
	 * @param resp
	 * @param roleName
	 * @param remark
	 * @param roleMenuStr
	 * @return
	 */
	@RequestMapping(value = "/doAddRole.do", method = RequestMethod.POST)
	public ResponseEntity<String> doAddRole(HttpServletRequest request,
			HttpServletResponse resp,
			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
			@RequestParam(value = "remark", required = false, defaultValue = "0") String remark,
			@RequestParam(value = "roleMenuStr", required = false, defaultValue = "0") String roleMenuStr) {
		HttpSession session = request.getSession();
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
    	logger.info("controller:CentralAction..角色新增!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
    	try {
			
			if (null != _loginUser) {
				SysRole newSysRole = new SysRole();
				newSysRole.setOperateId(_loginUser.getId());
				newSysRole.setRemark(remark);
				newSysRole.setRoleName(roleName);
				newSysRole.setCreateTime(TimeUtil.now());
				
				roleService.addSysRole(newSysRole, roleMenuStr);
				msg = "角色添加成功!";
			}else{
				msg = "用户未登录!";
			}
		} catch (SPlatformServiceException e) {
			e.printStackTrace();
			msg = "新增出现异常,请稍等..";
			return new ResponseEntity<String>("<script>parent.parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.parent.close(); parent.parent.location.href='" + WebUtils.formatURI(request, "/romanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
    	logger.info("controller:添加角色结束!");
		return new ResponseEntity<String>("<script>parent.parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.parent.close(); parent.parent.location.href='" + WebUtils.formatURI(request, "/romanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}

	
	/**
	 * 当前的角色删除
	 * @return
	 */
	@RequestMapping(value = "/doDelRole.do", method = RequestMethod.POST)
	public ResponseEntity<String> doDelRole(
			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..组织角色删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			//get role
			SysRole role = roleService.findSysRole(roleId);

			if(null != role){
				roleService.delRole(role);
				msg="角色删除成功!";
			}else{
				msg="角色不存在!";
			}
			
		}catch(Exception e){
			logger.error("controller:删除角色异常!"+roleName,e);
			msg="删除角色出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/romanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:删除角色结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/romanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * 跳转角色编辑页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/toEditRole.do")
    public ModelAndView roleEditPage(@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
    		HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/role/role_edit");
		
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);		
		try {
			if(null == _loginUser){
				//用户未登录
				return null;
			}
			/**
			 * 所有菜单节点  缓存获取
			 */
			List<SysMenu> allMenuList = (List<SysMenu>) session.getAttribute("menuList");		
			List<SysMenu> roleMenuList = roleService.getRoleMenuList(roleId);
			SysRole sysRole = roleService.findSysRole(roleId);
			
			//所有菜单数据串 格式：{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			List<String> items = new ArrayList<String>();
			String _temp = "{ id:'0', pId:'-1', name:"+"'全选'"+",iconOpen:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/1_open.png'"+", iconClose:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/1_close.png'"+",open:true}";
			items.add(_temp);
			String checked = "";
			//处理菜单选择列表
	    	for(SysMenu menu:allMenuList){
	    		
	    		for(SysMenu roleMenu : roleMenuList){
	    			if(roleMenu.getId() == menu.getId()){
	    				//logger.info("存在此菜单..."+menu.getId()+menu.getMenuName());
	    				checked=", checked:true";
	    			}
	    		}
	    		_temp = "{id:'"+menu.getId()+"',pId:'"+menu.getMenuPid()+"',name:'"+
	    		menu.getMenuName()+"'"+",icon:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/2.png'";
	    		if(menu.getHasChild() == 1){
	    			//存在子菜单默认打开
	    			_temp+=",icon:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/4.png'"+",open:true"+checked;
	    		}else{
	    			_temp += checked;
	    		}
	    		_temp += "}";
	    		
	    		items.add(_temp);//节点追加
	    		checked = "";//清空复选内容
	    	}
	    	
	    	//格式转换
			menuStrs = JsonUtils.toJson(items);
			menuStrs = menuStrs.replaceAll("\"", "");
			
			model.addObject("menuStrs", menuStrs);
			model.addObject("sysRole", sysRole);
	    	logger.info("menuStrs:"+menuStrs.toString());
		} catch (Exception e) {
			logger.error("跳转角色编辑页面error...");
		}
        return model;
    }
	
	
	
	/***
	 * 角色编辑
	 * @param request
	 * @param resp
	 * @param roleName
	 * @param remark
	 * @param roleMenuStr
	 * @return
	 */
	@RequestMapping(value = "/doEditRole.do", method = RequestMethod.POST)
	public ResponseEntity<String> doEditRole(HttpServletRequest request,
			HttpServletResponse resp,
			@RequestParam(value = "roleId", required = false, defaultValue = "") Integer roleId,
			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
			@RequestParam(value = "remark", required = false, defaultValue = "0") String remark,
			@RequestParam(value = "roleMenuStr", required = false, defaultValue = "0") String roleMenuStr) {
		HttpSession session = request.getSession();
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
    	logger.info("controller:CentralAction..角色编辑!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
    	try {
			
			if (null != _loginUser) {//get role
				SysRole role = roleService.findSysRole(roleId);
				role.setOperateId(_loginUser.getId());
				role.setRemark(remark);
				role.setRoleName(roleName);
				role.setCreateTime(TimeUtil.now());
				
				roleService.updateRoleInfo(role, roleMenuStr);
				msg = "角色编辑成功!";
			}else{
				msg = "用户未登录!";
			}
		} catch (SPlatformServiceException e) {
			e.printStackTrace();
			msg = "编辑出现异常,请稍等..";
			return new ResponseEntity<String>("<script>parent.parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.parent.close(); parent.parent.location.href='" + WebUtils.formatURI(request, "/romanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
    	logger.info("controller:添加角色结束!");
		return new ResponseEntity<String>("<script>parent.parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.parent.close(); parent.parent.location.href='" + WebUtils.formatURI(request, "/romanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
//    /**
//	 * 当前组织下的角色添加
//	 * @return
//	 */
//	@RequestMapping(value = "/roleAdd.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleAdd(
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色添加!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		////role.setGroupId(groupId);
//		role.setCreateTime(TimeUtil.getTime(14));
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.addRole(role);
//			msg="角色添加成功!";
//		}catch(Exception e){
//			logger.error("controller:添加角色异常!"+roleName,e);
//			msg="添加角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:添加角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	/**
//	 * 当前组织下的角色编辑
//	 * @return
//	 */
//	@RequestMapping(value = "/roleEdit.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleEdit(
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色编辑!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		role.setId(roleId);
//		//role.setGroupId(groupId);
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.editRole(role);
//			msg="角色编辑成功!";
//		}catch(Exception e){
//			logger.error("controller:编辑角色异常!"+roleName,e);
//			msg="编辑角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:编辑角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	
//	/**
//	 * 当前组织下的角色删除
//	 * @return
//	 */
//	@RequestMapping(value = "/roleDel.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleDel(
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色删除!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		role.setId(roleId);
//		//role.setGroupId(groupId);
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.delRole(role);
//			msg="角色删除成功!";
//		}catch(Exception e){
//			logger.error("controller:删除角色异常!"+roleName,e);
//			msg="删除角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:删除角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//
//	
//	
//	
//	
//	/**
//	 * 当前组织下的角色添加
//	 * @return
//	 */
//	@RequestMapping(value = "/roleAddExt.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleAddExt(
//			@RequestParam(value = "groupIndex", required = false, defaultValue = "") Integer gIndex,
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色添加!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		//role.setGroupId(groupId);
//		role.setCreateTime(TimeUtil.getTime(14));
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.addRole(role);
//			msg="角色添加成功!";
//		}catch(Exception e){
//			logger.error("controller:添加角色异常!"+roleName,e);
//			msg="添加角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageExt.do?gIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:添加角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageExt.do?gIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	/**
//	 * 当前组织下的角色编辑
//	 * @return
//	 */
//	@RequestMapping(value = "/roleEditExt.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleEditExt(
//			@RequestParam(value = "groupIndex", required = false, defaultValue = "") Integer gIndex,
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色编辑!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		role.setId(roleId);
//		//role.setGroupId(groupId);
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.editRole(role);
//			msg="角色编辑成功!";
//		}catch(Exception e){
//			logger.error("controller:编辑角色异常!"+roleName,e);
//			msg="编辑角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageExt.do?gIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:编辑角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageExt.do?gIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	
//	/**
//	 * 当前组织下的角色删除
//	 * @return
//	 */
//	@RequestMapping(value = "/roleDelExt.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleDelExt(
//			@RequestParam(value = "groupIndex", required = false, defaultValue = "") Integer gIndex,
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色删除!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		role.setId(roleId);
//		//role.setGroupId(groupId);
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.delRole(role);
//			msg="角色删除成功!";
//		}catch(Exception e){
//			logger.error("controller:删除角色异常!"+roleName,e);
//			msg="删除角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageExt.do?gIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:删除角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageExt.do?gIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	
//	
//	/**
//	 * 当前组织下的角色添加
//	 * @return
//	 */
//	@RequestMapping(value = "/roleAddExtAu.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleAddExtAu(
//			@RequestParam(value = "groupIndex", required = false, defaultValue = "") Integer gIndex,
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色添加!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		//role.setGroupId(groupId);
//		role.setCreateTime(TimeUtil.getTime(14));
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.addRole(role);
//			msg="角色添加成功!";
//		}catch(Exception e){
//			logger.error("controller:添加角色异常!"+roleName,e);
//			msg="添加角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageAuser.do?groupIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:添加角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageAuser.do?groupIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	/**
//	 * 当前组织下的角色编辑
//	 * @return
//	 */
//	@RequestMapping(value = "/roleEditExtAu.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleEditExtAu(
//			@RequestParam(value = "groupIndex", required = false, defaultValue = "") Integer gIndex,
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色编辑!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		role.setId(roleId);
//		//role.setGroupId(groupId);
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.editRole(role);
//			msg="角色编辑成功!";
//		}catch(Exception e){
//			logger.error("controller:编辑角色异常!"+roleName,e);
//			msg="编辑角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageAuser.do?groupIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:编辑角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageAuser.do?groupIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
//	
//	
//	/**
//	 * 当前组织下的角色删除
//	 * @return
//	 */
//	@RequestMapping(value = "/roleDelExtAu.do", method = RequestMethod.POST)
//	public ResponseEntity<String> roleDelExtAu(
//			@RequestParam(value = "groupIndex", required = false, defaultValue = "") Integer gIndex,
//			@RequestParam(value = "groupId", required = false, defaultValue = "0") int groupId,
//			@RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
//			@RequestParam(value = "roleName", required = false, defaultValue = "0") String roleName,
//			HttpServletRequest request,HttpServletResponse response,
//			Model model) {
//		logger.info("controller:..组织角色删除!");
//		String msg="";
//		boolean isCorrect = true;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		//new role
//		SysRole role = new SysRole();
//		role.setId(roleId);
//		//role.setGroupId(groupId);
//		role.setRoleName(roleName);
//		role.setRemark("测试");
//		role.setOperateId(1);//操作人员id，可以通过session获取
//		
//		try{
//			roleService.delRole(role);
//			msg="角色删除成功!";
//		}catch(Exception e){
//			logger.error("controller:删除角色异常!"+roleName,e);
//			msg="删除角色出现异常";
//			model.addAttribute("msg", msg);
//			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageAuser.do?groupIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//			
//		}
//		logger.info("controller:删除角色结束!");
//		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/gmanageAuser.do?groupIndex="+gIndex)+"'</script>",responseHeaders, HttpStatus.CREATED);
//	}
	
	
	
	
	
	/*******************************************************************
	 *********************功能分割***************************
	 ********************************************************************/
	
	/**
	 * 当前组织下的角色按钮设置
	 * @return
	 */
	@RequestMapping(value = "/addRoleBtn.do", method = RequestMethod.POST)
	public @ResponseBody String editRoleBtn(
			@RequestParam(value = "roleId", required = false, defaultValue = "") Integer roleId,
			@RequestParam(value = "operateId", required = false, defaultValue = "") Integer operateId,
			@RequestParam(value = "model_code", required = false, defaultValue = "0") String model_code,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..角色按钮编辑!");
		String msg="";
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			/*通过model_code查询某个操作实体*/
			SysOperate sysOperate = operateService.findSysOperate(model_code);
			
			SysRole sysRole = roleService.findSysRole(roleId);//获取角色
			Set<SysOperate> operSet = sysRole.getOperateSet();//操作集合
			
			Set<SysOperate> oldOperSet = new HashSet<SysOperate>();//之前的操作集合
			Set<SysOperate> newOperSet = new HashSet<SysOperate>();//新的操作集合
			for(SysOperate sysOper:operSet){
				//循环处理操作按钮
				if(!sysOper.getOperateCode().equals(sysOperate.getOperateCode())){
					//添加进去新的
					newOperSet.add(sysOperate);
				}else{
					//添加之前的
					newOperSet.add(sysOper);
					oldOperSet.add(sysOper);
				}
			}
			
			//追加新的操作按钮
			sysRole.getOperateSet().addAll(newOperSet);
			roleService.editRole(sysRole);
			msg="按钮编辑完成";
		}catch(Exception e){
			logger.error("controller:角色按钮编辑异常!"+roleId+","+model_code,e);
			msg="角色按钮编辑出现异常";
			model.addAttribute("msg", msg);
			return ResponseUtils.newJsonOKResp(msg);
			
		}
		logger.info("controller:角色按钮编辑结束!");
		return ResponseUtils.newJsonOKResp(msg);
	}
	
	/**
	 * 当前组织下的角色按钮删除
	 * @return
	 */
	@RequestMapping(value = "/delRoleBtn.do", method = RequestMethod.POST)
	public @ResponseBody String delRoleBtn(
			@RequestParam(value = "roleId", required = false, defaultValue = "") Integer roleId,
			@RequestParam(value = "model_code", required = false, defaultValue = "0") String model_code,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..角色按钮删除!");
		String msg="";
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			/*通过model_code查询某个操作实体*/
			SysOperate sysOperate = operateService.findSysOperate(model_code);
			
			SysRole sysRole = roleService.findSysRole(roleId);//获取角色
			Set<SysOperate> operSet = sysRole.getOperateSet();//操作集合
			
			Set<SysOperate> oldOperSet = new HashSet<SysOperate>();//之前的操作集合
			Set<SysOperate> delOperSet = new HashSet<SysOperate>();//新的操作集合
			for(SysOperate sysOper:operSet){
				//循环处理操作按钮
				if(sysOper.getOperateCode().equals(sysOperate.getOperateCode())){
					//存在当前按钮
					delOperSet.add(sysOperate);
				}else{
					//添加之前的
					oldOperSet.add(sysOper);
				}
			}
			
			//追加新的操作按钮
			sysRole.getOperateSet().removeAll(delOperSet);
			sysRole.getOperateSet().addAll(oldOperSet);
			roleService.editRole(sysRole);
			msg="按钮取消完成";
		}catch(Exception e){
			logger.error("controller:角色按钮删除异常!"+roleId+","+model_code,e);
			msg="角色按钮删除出现异常";
			model.addAttribute("msg", msg);
			return ResponseUtils.newJsonOKResp(msg);
			
		}
		logger.info("controller:角色按钮删除结束!");
		return ResponseUtils.newJsonOKResp(msg);
	}
	
}
