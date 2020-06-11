package com.hrbuedu.cn.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hrbuedu.cn.dao.mapper.MailMapper;
import com.hrbuedu.cn.model.sysUser.Mail;
import com.hrbuedu.cn.model.sysUser.MailExample;
import com.hrbuedu.cn.service.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Resource
	MailMapper mailMapper;
	@Resource
	MailService mailService;
	
	/*
	 * 会员的邮件信息展示
	 */
	@Override
	public List<Mail> show(String memberId) {
		System.out.println("会员id" + memberId);
		MailExample mailExample = new MailExample();
		MailExample.Criteria criteria = mailExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		return mailMapper.selectByMember(memberId);
	}

	/*
	 * 会员删除邮件
	 */
	@Override
	public int delete(HttpServletRequest request, String mailId) {
		MailExample mailExample = new MailExample();
		MailExample.Criteria criteria = mailExample.createCriteria();
		criteria.andIdEqualTo(mailId);
		return mailMapper.deleteByExample(mailExample);
	}

	/*
	 * 会员批量删除邮件
	 */
	@Override
	public int deleteAll(List<String> id) {
		HashMap<String, List<String>> map = new HashMap<>();
		map.put("id", id);
		return mailMapper.deleteByAll(map);
	}	
	
	/*
	 * 邮件总数
	 */
	@Override
	public int num(HttpServletRequest request, String memberId) {
		return mailMapper.selectByMailNum(memberId);
	}

	/*
	 * 今天的邮件
	 */
	@Override
	public List<Mail> today(HttpServletRequest request) {
		MailExample mailExample = new MailExample();
		return mailMapper.selectByToday(mailExample);
	}

	/*
	 * 昨天的邮件
	 */
	@Override
	public List<Mail> yesterday(HttpServletRequest request) {
		MailExample mailExample = new MailExample();
		return mailMapper.selectByYesterday(mailExample);
	}

	/*
	 * 这周的邮件
	 */
	@Override
	public List<Mail> thisWeek(HttpServletRequest request) {
		MailExample mailExample = new MailExample();
		return mailMapper.selectByThisWeek(mailExample);
	}

	/*
	 * 上周的邮件
	 */
	@Override
	public List<Mail> lastWeek(HttpServletRequest request) {
		MailExample mailExample = new MailExample();
		return mailMapper.selectByLastWeek(mailExample);
	}

	/*
	 * 普通邮件数量
	 */
	@Override
	public int normalNum(String memberId) {
		MailExample mailExample = new MailExample();
		MailExample.Criteria criteria = mailExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		return mailMapper.selectByNormalNum(memberId);
	}

	/*
	 * 垃圾邮件数量
	 */
	@Override
	public int spamNum(String memberId) {
		MailExample mailExample = new MailExample();
		MailExample.Criteria criteria = mailExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		return mailMapper.selectBySpamNum(memberId);
	}

	
}
