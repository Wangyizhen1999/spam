package com.hrbuedu.cn.controller.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrbuedu.cn.dao.mapper.MailMapper;
import com.hrbuedu.cn.dao.mapper.MemberMapper;
import com.hrbuedu.cn.model.sysUser.Mail;
import com.hrbuedu.cn.model.sysUser.Member;
import com.hrbuedu.cn.service.MailService;
import com.hrbuedu.cn.service.MemberService;

@RestController
@RequestMapping("mail")
public class MailController {
	
	@Resource
	MailService mailService;
	@Resource
	MemberService memberService;
	@Resource
	MailMapper mailMapper;
	@Resource
	MemberMapper memberMapper;
	
	/*
	 * 会员邮件信息展示
	 */
	@RequestMapping(value = "mailShow", method = RequestMethod.POST)
	public Map<String, Object> show(HttpServletRequest request, String memberId){
		System.out.println("mailShow start");
		HttpSession session = request.getSession();
		session.setAttribute(memberId, "memberId");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mail> showList = mailService.show(memberId);
		map.put("showList", showList);
		int mailNum = mailService.num(request, memberId);
		map.put("mailNum", mailNum);
		Member member = new Member();
		member.setMailNum(mailNum);
		member.setId(memberId);
		memberMapper.updateByNum(member);
		System.out.println("用户邮件总数" + mailNum);
		return map;
	}
	
	/*
	 * 删除单个邮件
	 */
	@RequestMapping(value = "mailDelete", method = RequestMethod.GET)
	public Map<String, Object> delete(HttpServletRequest request, String mailId){
		Map<String, Object> map = new HashMap<String, Object>();
		int deleteList = mailService.delete(request, mailId);
		map.put("m", "恭喜删除成功");
		map.put("code", 1);
		return map;
	}
	
	/*
	 * 批量删除邮件
	 */
	@RequestMapping(value = "mailDeleteAll", method = RequestMethod.GET)
	public int deleteAll(HttpServletRequest request, String id){
		List idList = new ArrayList();
		String[] strs = id.split(",");
		for (String str : strs) {
			idList.add(str);
		}
		return mailService.deleteAll(idList);
	}
	
//	@RequestMapping(value = "mailDeleteAll", method = RequestMethod.GET)
//	public String deleteAll(HttpServletRequest request, String mailId) {
//		System.out.println(mailId);
//		String[] strs = mailId.split(",");
//		List<Integer> ids = new ArrayList<>();
//		for (String str : strs) {
//			ids.add(Integer.parseInt(str));
//		}
//		return mailService.deleteAll(ids);
//	}
	
	/*
	 * 统计垃圾邮件以及普通邮件
	 */
	@RequestMapping(value="/mailNum",method=RequestMethod.POST)	
	public Map<String,Object> mailNum(HttpServletRequest request, String memberId){
		HttpSession session = request.getSession(); 
		Map<String,Object> map=new HashMap<String,Object>();
		int normalNum=mailService.normalNum(memberId);
		int spamNum=mailService.spamNum(memberId);
		map.put("normalNum", normalNum);
		map.put("spamNum", spamNum);
		System.out.println("putong"+normalNum+"laji"+spamNum);
		return map; 
	}

	/*
	 * 今天的邮件
	 */
	@RequestMapping(value = "mailToday", method = RequestMethod.GET) 
	public Map<String, Object> today(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mail> todayList = mailService.today(request);
		map.put("todayList", todayList);
		return map;
	}
	/*
	 * 昨天的邮件
	 */
	@RequestMapping(value = "mailYesterday", method = RequestMethod.GET) 
	public Map<String, Object> yesterday(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mail> yesterdayList = mailService.yesterday(request);
		map.put("yesterdayList", yesterdayList);
		return map;
	}
	/*
	 * 本周的邮件
	 */
	@RequestMapping(value = "mailthisWeek", method = RequestMethod.GET) 
	public Map<String, Object> thisWeek(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mail> weekList = mailService.thisWeek(request);
		map.put("weekList", weekList);
		return map;
	}
	/*
	 * 上周的邮件
	 */
	@RequestMapping(value = "mailastWeek", method = RequestMethod.GET) 
	public Map<String, Object> lastWeek(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mail> lastList = mailService.lastWeek(request);
		map.put("lastList", lastList);
		return map;
	}
}
