package com.hrbuedu.cn.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrbuedu.cn.controller.id.Sequence;
import com.hrbuedu.cn.dao.mapper.MemberMapper;
import com.hrbuedu.cn.model.sysUser.Member;
import com.hrbuedu.cn.model.sysUser.MemberExample;
import com.hrbuedu.cn.service.MemberService;
import com.hrbuedu.cn.util.MD5Util;

@Service
public class MemberServiceImpl implements MemberService {

	@Resource
	MemberService memberService;
	@Resource
	MemberMapper memberMapper;

	/*
	 * 会员登录
	 */
	@Override
	public List<Member> login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String password = MD5Util.getMD5(request.getParameter("password").getBytes());
		String inputCode = request.getParameter("inputCode");
		System.out.println("账号：" + account + "密码：" + password);
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andPasswordEqualTo(password);
		return memberMapper.selectByExample(memberExample);
	}

	/*
	 * 会员注册
	 */
	@Override
	public int register(HttpServletRequest request) {
		System.out.println("开始了哈哈哈");
		String account = request.getParameter("account");
		String phone = request.getParameter("phone");
		String problem = request.getParameter("problem");
		String answer = request.getParameter("answer");
		String password = MD5Util.getMD5(request.getParameter("password").getBytes());
		System.out.println(account + phone + problem + answer + password);

		Member member = new Member();
		member.setId(Sequence.nextId());
		member.setAccount(account);
		member.setPhone(phone);
		member.setProblem(problem);
		member.setAnswer(answer);
		member.setPassword(password);
		member.setCt(new Date());
		return memberMapper.insert(member);
	}

	/*
	 * 会员找回密码--账号、手机号的验证
	 */
	@Override
	public List<Member> find(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String phone = request.getParameter("phone");
		System.out.println("账号：" + account + "手机号：" + phone);
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andPhoneEqualTo(phone);
		return memberMapper.selectByExample(memberExample);
	}

	/*
	 * 会员找回密码--显示密保问题
	 */
	@Override
	public List<Member> protect(String memberId) {
		System.out.println(" memberProtect start");
		System.out.println("会员id" + memberId);
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdEqualTo(memberId);
		return memberMapper.selectByExample(memberExample);
	}

	/*
	 * 会员找回密码--修改密码
	 */
	@Override
	public List<Member> findPassword(HttpServletRequest request, String memberId) {
		String answer = request.getParameter("answer");
		String password = MD5Util.getMD5(request.getParameter("password").getBytes());
		String inputCode = request.getParameter("inputCode");
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdEqualTo(memberId);
		criteria.andAnswerEqualTo(answer);
		return memberMapper.selectByExample(memberExample);
	}

	/*
	 * 管理员端会员信息展示
	 */
	@Override
	public List<Member> showAll(HttpServletRequest request) {
		MemberExample memberExample = new MemberExample();
		return memberMapper.selectByExample(memberExample);
	}
	
	/*
	 * 会员信息展示
	 */
	@Override
	public List<Member> show(String memberId) {
		System.out.println("memberShow start");
		System.out.println("会员id" + memberId);
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdEqualTo(memberId);
		return memberMapper.selectByExample(memberExample);
	}

	/*
	 * 会员头像展示
	 */
	@Override
	public Member getImg(String id) {
		return memberMapper.selectByPrimaryKey(id);
	}

	/*
	 * 会员信息完善
	 */
	@Override
	public int improve(HttpServletRequest request, Member member, MultipartFile file, String memberId, String name,
			String mailbox, String gender, String id) throws Exception {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			// 获取客户端传图图片的输入流
			InputStream ins = file.getInputStream();
			byte[] buffer = new byte[1024];// bit---byte---1k---1m
			int len = 0;
			// 字节输出流
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = ins.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
			byte picture[] = bos.toByteArray();
			// 调用接口对图片进行存储

			member.setId(id);
			member.setGender(Integer.parseInt(gender));
			member.setName(name);
			member.setMailbox(mailbox);
			member.setImg(picture);
			System.out.println("name" + member.getName());
			return memberMapper.updateByMember(member);
		} catch (Exception e) {

			return 1;
		}
	}

	/* 
	 * 会员查找旧密码
	 */
	@Override
	public List<Member> update(HttpServletRequest request, String memberId) {
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdEqualTo(memberId);
		return memberMapper.selectByExample(memberExample);
	}

	/*
	 * 会员修改密码
	 */
	@Override
	public int daUpdate(HttpServletRequest request, String memberId) {
		String newPassword = MD5Util.getMD5(request.getParameter("newPassword").getBytes());
		Member member = new Member();
		member.setPassword(newPassword);
		member.setId(memberId);
		return memberMapper.updateByPrimaryKeySelective(member);
	}
}
