package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrbuedu.cn.model.sysUser.Mail;

public interface MailService {

	List<Mail> show(String memberId);

	int delete(HttpServletRequest request, String mailId);

	int num(HttpServletRequest request, String memberId);

	List<Mail> today(HttpServletRequest request);

	List<Mail> yesterday(HttpServletRequest request);

	List<Mail> thisWeek(HttpServletRequest request);

	List<Mail> lastWeek(HttpServletRequest request);

	int deleteAll(List<String> id);

	int normalNum(String memberId);

	int spamNum(String memberId);


}
