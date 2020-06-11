package com.hrbuedu.cn.controller.member;

import java.util.HashMap;
import java.util.List;
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

import com.hrbuedu.cn.dao.mapper.MemberMapper;
import com.hrbuedu.cn.model.sysUser.Member;
import com.hrbuedu.cn.service.MemberService;
import com.hrbuedu.cn.util.MD5Util;

@RestController
@RequestMapping("member")
public class MemberController {

	@Resource
	MemberMapper memberMapper;
	@Resource
	MemberService memberService;

	/*
	 * 会员登录
	 */
	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> list = memberService.login(request);
		if (request.getParameter("account").length() == 0) {
			map.put("prompt", "账号不能为空");
		} else if (request.getParameter("password").length() == 0) {
			map.put("prompt", "密码不能为空");
		} else if (list.size() == 0) {
			map.put("prompt", "请输入正确的账号和密码");
		} else if (request.getParameter("inputCode").length() == 0) {
			map.put("prompt", "验证码不能为空");
		} else if (!request.getParameter("inputCode").equalsIgnoreCase((String) session.getAttribute("code"))) {
			map.put("prompt", "请输入正确的验证码 ");
		} else {
			map.put("gain", 1);
			map.put("memberId", list.get(0).getId());
			map.put("memberAccount", list.get(0).getAccount());
			System.out.println(list.get(0).getAccount());
		}
		return map;
	}

	/*
	 * 会员注册
	 */
	@RequestMapping(value = "memberRegister", method = RequestMethod.POST)
	public Map<String, Object> memberregister(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		int list = memberService.register(request);
		if (request.getParameter("account").length() == 0) {
			map.put("prompt", "账号不能为空");
		} else if (request.getParameter("password").length() == 0) {
			map.put("prompt", "密码不能为空");
		} else if (request.getParameter("problem").length() == 0) {
			map.put("prompt", "密保问题不能为空");
		} else if (request.getParameter("querenPassword").length() == 0) {
			map.put("prompt", "复验密码不能为空");
		} else if (request.getParameter("answer").length() == 0) {
			map.put("prompt", "密保答案不能为空");
		} else if (request.getParameter("phone").length() == 0) {
			map.put("prompt", "手机号不能为空");
		} else {
			map.put("prompt", "注册成功");
		}
		return map;
	}

	/*
	 * 会员找回密码--账号、手机号的验证
	 */
	@RequestMapping(value = "memberFind", method = RequestMethod.POST)
	public Map<String, Object> find(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> list = memberService.find(request);
		if (request.getParameter("account").length() == 0) {
			map.put("prompt", "账号不能为空");
		} else if (request.getParameter("phone").length() == 0) {
			map.put("prompt", "手机号不能为空");
		} else if (list.size() == 0) {
			map.put("prompt", "请输入正确的账号和手机号");
		} else {
				map.put("gain", 1);
				map.put("memberId", list.get(0).getId());
				map.put("memberAccount", list.get(0).getAccount());
				map.put("memberPhone", list.get(0).getPhone());
				System.out.println(list.get(0).getId() + list.get(0).getAccount() + list.get(0).getPhone());
		}
		return map;
	}

	/*
	 * 会员找回密码--显示密保问题
	 */
	@RequestMapping(value = "/memberProtect", method = RequestMethod.POST)
	public Map<String, Object> protect(HttpServletRequest request, String memberId) {
		System.out.println("memberProtect start");
		HttpSession session = request.getSession();
		session.setAttribute("memberId", memberId);
		System.out.println(memberId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> protectList = memberService.protect(memberId);
		map.put("protectList", protectList);
		return map;
	}

	/*
	 * 会员找回密码--修改密码
	 */
	@RequestMapping(value = "memberfindPassword", method = RequestMethod.POST)
	public Map<String, Object> findPassword(HttpServletRequest request, String memberId) {
		System.out.println("findpassword start");
		HttpSession session = request.getSession();
		session.setAttribute("memberId", memberId);
		String password = MD5Util.getMD5(request.getParameter("password").getBytes());
		String querenPassword = MD5Util.getMD5(request.getParameter("querenPassword").getBytes());
		System.out.println(memberId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> list = memberService.findPassword(request, memberId);
		if (request.getParameter("answer").length() == 0) {
			map.put("prompt", "密保答案不能为空");
		} else if (request.getParameter("password").length() == 0) {
			map.put("prompt", "新密码不能为空");
		} else if (request.getParameter("querenPassword").length() == 0) {
			map.put("prompt", "新密码不能为空");
		} else if (list.size() == 0) {
			map.put("prompt", "请输入正确的密保答案");
		} else if(!password.equals(querenPassword)) {
			map.put("prompt", "请输入正确的新密码");
		}else {
				Member member = new Member();
				member.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));
				member.setAnswer(request.getParameter("answer"));
				member.setId(memberId);
				memberMapper.updateByProblem(member);
				map.put("prompt", "找回密码成功");
			}
		return map;
	}
	
	/*
	 * 管理员端会员信息展示
	 */
	@RequestMapping(value = "/managerShow", method = RequestMethod.POST)
	public Map<String, Object> show(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> showAllList = memberService.showAll(request);
		map.put("showAllList", showAllList);
		return map;
	}
	/*
	 * 会员信息展示
	 */
	@RequestMapping(value = "/memberShow", method = RequestMethod.POST)
	public Map<String, Object> show(HttpServletRequest request, String memberId, MultipartFile file) {
		System.out.println("memberList start");
		HttpSession session = request.getSession();
		session.setAttribute("memberId", memberId);
		System.out.println(memberId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> showList = memberService.show(memberId);
		map.put("showList", showList);
		return map;
	}
	
	/*
	 * 会员头像展示
	 */
	@RequestMapping(value = "/headImg", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> headImg(String id) throws Exception{

		byte[] imageContent ;
		// 根据id获取当前用户的信息
	    Member member = memberService.getImg(id);
		imageContent = member.getImg();
		System.out.println("图片" + member.getImg());
		// 设置http头部信息
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		// 返回响应实体
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	/*
	 * 会员完善信息
	 */
	@RequestMapping("/memberImprove")	
	public String improve(HttpServletRequest request, MultipartFile file, String memberId, String name, String mailbox, String gender, String id) throws Exception{
		System.out.println("memberImprove start");
		Member member = new Member();
		memberService.improve(request, member, file, memberId, name, mailbox, gender, id);		            		
		return "";	
	}
	
	/*
	 * 会员修改密码
	 */
	@RequestMapping(value = "/memberupdatePassword", method = RequestMethod.POST)
	public Map<String, Object> update(HttpServletRequest request, String memberId){
		System.out.println("memberupdatePassword start");
		Map<String,Object> map=new HashMap<String,Object>();
		List<Member> list = memberService.update(request, memberId);
		String oldPassword= MD5Util.getMD5(request.getParameter("oldPassword").getBytes());
		String newPassword = MD5Util.getMD5(request.getParameter("newPassword").getBytes());
		String querenPassword = MD5Util.getMD5(request.getParameter("querenPassword").getBytes());
		System.out.println("旧密码：" + oldPassword + "新密码：" +  newPassword);
		if(!newPassword.equals(querenPassword)) {
			map.put("prompt", "请输入正确的新密码");
		}else if(!oldPassword.equals(list.get(0).getPassword())) {
			map.put("prompt", "请输入正确的旧密码");
		}else {
			int member = memberService.daUpdate(request, memberId);
			map.put("prompt", "修改密码成功");
		}
		return map;		
	}

}
