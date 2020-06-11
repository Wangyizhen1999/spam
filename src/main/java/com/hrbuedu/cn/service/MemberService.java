package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hrbuedu.cn.model.sysUser.Member;

public interface MemberService {

	public int register(HttpServletRequest request);

	public List<Member> login(HttpServletRequest request);

	public List<Member> find(HttpServletRequest request);

	public List<Member> protect(String memberId);

	public List<Member> findPassword(HttpServletRequest request, String memberId);

	public List<Member> show(String memberId);

	public Member getImg(String id);

	public int improve(HttpServletRequest request, Member member, MultipartFile file, String memberId, String name,
			String mailbox, String gender, String id) throws Exception;

	public List<Member> update(HttpServletRequest request, String memberId);

	public int daUpdate(HttpServletRequest request, String memberId);

	public List<Member> showAll(HttpServletRequest request);

	

}
