package com.hrbuedu.cn.controller.manager;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrbuedu.cn.dao.mapper.ManagerMapper;
import com.hrbuedu.cn.model.sysUser.Manager;
import com.hrbuedu.cn.model.sysUser.Member;
import com.hrbuedu.cn.service.ManagerService;
import com.hrbuedu.cn.util.MD5Util;

@RestController
@RequestMapping("manager")
public class ManagerController {
	@Resource
	ManagerMapper managerMapper;
	@Resource
	ManagerService managerService;
	
	/*
	 *  管理员登录
	 */
	@RequestMapping(value = "managerLogin", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request){
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Manager> list = managerService.login(request);
		if (request.getParameter("account").length() == 0) {
			map.put("prompt", "账号不能为空");
		}else if (request.getParameter("password").length() == 0) {
			map.put("prompt", "密码不能为空");
		}else if (list.size() == 0) {
			map.put("prompt", "请输入正确的账号和密码");
		}else if (request.getParameter("inputCode").length() == 0) {
			map.put("prompt", "验证码不能为空");
		}else if(!request.getParameter("inputCode").equalsIgnoreCase((String) session.getAttribute("code"))) {
			map.put("prompt", "请输入正确的验证码 ");
		}else {
			map.put("gain", 1);
			map.put("managerId", list.get(0).getId());
			map.put("managerAccount", list.get(0).getAccount());
			System.out.println(list.get(0).getAccount());
		}
		return map;
	}
	
	/*
	 * 管理员找回密码--账号、手机号的验证
	 */
	@RequestMapping(value = "managerFind", method = RequestMethod.POST)
	public Map<String, Object> find(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Manager> list = managerService.find(request);
		if (request.getParameter("account").length() == 0) {
			map.put("prompt", "账号不能为空");
		} else if (request.getParameter("phone").length() == 0) {
			map.put("prompt", "手机号不能为空");
		} else {
			if (list == null) {
				map.put("prompt", "请输入正确的账号");
			} else {
				map.put("gain", 1);
				map.put("managerId", list.get(0).getId());
				map.put("managerAccount", list.get(0).getAccount());
				map.put("managerPhone", list.get(0).getPhone());
				System.out.println(list.get(0).getId() + list.get(0).getAccount() + list.get(0).getPhone());
			}
		}
		return map;
	}

	/*
	 * 管理员找回密码--显示密保问题
	 */
	@RequestMapping(value = "/managerProtect", method = RequestMethod.GET)
	public Map<String, Object> protect(HttpServletRequest request, String managerId) {
		System.out.println("managerProtect start");
		HttpSession session = request.getSession();
		session.setAttribute("managerId", managerId);
		System.out.println(managerId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Manager> protectList = managerService.protect(managerId);
		map.put("protectList", protectList);
		return map;
	}

	/*
	 * 管理员找回密码--修改密码
	 */
	@RequestMapping(value = "managerfindPassword", method = RequestMethod.POST)
	public Map<String, Object> findPassword(HttpServletRequest request, String managerId) {
		System.out.println("findpassword start");
		HttpSession session = request.getSession();
		session.setAttribute("managerId", managerId);
		System.out.println(managerId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Manager> list = managerService.findPassword(request, managerId);
		if (request.getParameter("answer").length() == 0) {
			map.put("prompt", "密保答案不能为空");
		} else if (request.getParameter("password").length() == 0) {
			map.put("prompt", "新密码不能为空");
		} else if (!request.getParameter("inputCode").equalsIgnoreCase((String) session.getAttribute("code"))) {
			map.put("prompt", "请输入正确的验证码");
		} else {
			if (list == null) {
				map.put("prompt", "密保答案输入有误");
			} else {
				Manager manager = new Manager();
				manager.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));
				manager.setAnswer(request.getParameter("answer"));
				manager.setId(managerId);
				managerMapper.updateByProblem(manager);
				map.put("prompt", "找回密码成功");

			}
		}
		return map;
	}
	
	/*
	 * 管理员信息展示
	 */
	@RequestMapping(value = "/managerShow", method = RequestMethod.GET)
	public Map<String, Object> show(HttpServletRequest request, String managerId, MultipartFile file) {
		System.out.println("managerList start");
		HttpSession session = request.getSession();
		session.setAttribute("managerId", managerId);
		System.out.println(managerId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Manager> showList = managerService.show(managerId);
		map.put("showList", showList);
		return map;
	}
	
	/*
	 * 管理员头像展示
	 */
	@RequestMapping(value = "/headImg", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> headImg(String id) throws Exception{

		byte[] imageContent ;
		// 根据id获取当前用户的信息
	    Manager manager = managerService.getImg(id);
		imageContent = manager.getImg();
		System.out.println("图片" + manager.getImg());
		// 设置http头部信息
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		// 返回响应实体
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	/*
	 * 管理员完善信息
	 */
	@RequestMapping("/managerImprove")	
	public String improve(HttpServletRequest request, MultipartFile file, String managerId, String mailbox) throws Exception{
		System.out.println("managerImprove start");
		Manager manager = new Manager();
		managerService.improve(manager, file, request, managerId, mailbox);		            		
		return " ";	
	}
	
	/*
	 * 管理员修改密码
	 */
	@RequestMapping(value = "/managerupdatePassword", method = RequestMethod.POST)
	public Map<String, Object> update(HttpServletRequest request, String managerId){
		System.out.println("memberupdatePassword start");
		Map<String,Object> map=new HashMap<String,Object>();
		List<Manager> list = managerService.update(request, managerId);
		String oldPassword= request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String querenPassword = request.getParameter("querenPassword");
		System.out.println("旧密码：" + oldPassword + "新密码：" +  newPassword);
		if(!newPassword.equals(querenPassword)) {
			map.put("prompt", "请输入正确的新密码");
		}else if(!oldPassword.equals(list.get(0).getPassword())) {
			map.put("prompt", "请输入正确的旧密码");
		}else {
			int manager = managerService.daUpdate(request, managerId);
			map.put("prompt", "修改密码成功");
		}
		return map;		
	}

}
