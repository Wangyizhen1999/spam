package com.hrbuedu.cn.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrbuedu.cn.dao.mapper.ManagerMapper;
import com.hrbuedu.cn.model.sysUser.Manager;
import com.hrbuedu.cn.model.sysUser.ManagerExample;
import com.hrbuedu.cn.service.ManagerService;
import com.hrbuedu.cn.util.MD5Util;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Resource
	ManagerMapper managerMapper;
	@Resource
	ManagerService managerService;
	
    /*
     *  管理员登录
     */
	@Override
	public List<Manager> login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
//		String password = MD5Util.getMD5(request.getParameter("password").getBytes());
		String password = request.getParameter("password");
		String inputCode = request.getParameter("inputCode");
		System.out.println("账号：" + account + "密码：" + password);
		ManagerExample managerExample = new ManagerExample();
		ManagerExample.Criteria criteria = managerExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andPasswordEqualTo(password);
		return managerMapper.selectByExample(managerExample);
	}

	/*
	 * 管理员找回密码--账号、手机号的验证
	 */
	@Override
	public List<Manager> find(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String phone = request.getParameter("phone");
		System.out.println("账号：" + account + "手机号：" + phone);
		ManagerExample managerExample = new ManagerExample();
		ManagerExample.Criteria criteria = managerExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andPasswordEqualTo(phone);
		return managerMapper.selectByExample(managerExample);
	}
	
	/*
	 * 管理员找回密码--显示密保问题
	 */
	@Override
	public List<Manager> protect(String managerId) {
		System.out.println(" managerProtect start");
		System.out.println("会员id" + managerId);
		ManagerExample managerExample = new ManagerExample();
		ManagerExample.Criteria criteria = managerExample.createCriteria();
		criteria.andIdEqualTo(managerId);
		return managerMapper.selectByExample(managerExample);
	}
	
	/*
	 * 管理员找回密码--修改密码
	 */
	@Override
	public List<Manager> findPassword(HttpServletRequest request, String managerId) {
		String answer = request.getParameter("answer");
		String password = MD5Util.getMD5(request.getParameter("password").getBytes());
		String inputCode = request.getParameter("inputCode");
		ManagerExample managerExample = new ManagerExample();
		ManagerExample.Criteria criteria = managerExample.createCriteria();
		criteria.andIdEqualTo(managerId);
		criteria.andAnswerEqualTo(answer);
	    return managerMapper.selectByExample(managerExample);
	}
	
	/*
	 * 管理员信息展示
	 */
	@Override
	public List<Manager> show(String managerId) {
		System.out.println("managerShow start");
		System.out.println("管理员id" + managerId);
		ManagerExample managerExample = new ManagerExample();
		ManagerExample.Criteria criteria = managerExample.createCriteria();
		criteria.andIdEqualTo(managerId);
	    return managerMapper.selectByExample(managerExample);
	}
	
	/*
	 * 管理员头像展示
	 */
	@Override
	public Manager getImg(String id) {
		return managerMapper.selectByPrimaryKey(id);
	}

	/*
	 * 管理员信息完善
	 */
	@Override
	public int improve(Manager manager, MultipartFile file, HttpServletRequest request, String managerId,
			String mailbox) throws Exception {
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

			manager.setId(managerId);
			manager.setMailbox(mailbox);
			manager.setImg(picture);
			System.out.println("name" + manager.getMailbox());
			return managerMapper.updateByManager(manager);
		} catch (Exception e) {
			return 1;
		}
	}

	/*
	 * 管理员查找旧密码
	 */
	@Override
	public List<Manager> update(HttpServletRequest request, String managerId) {
		ManagerExample managerExample = new ManagerExample();
		ManagerExample.Criteria criteria = managerExample.createCriteria();
		criteria.andIdEqualTo(managerId);
		return managerMapper.selectByExample(managerExample);
	}

	/*
	 * 管理员修改密码
	 */
	@Override
	public int daUpdate(HttpServletRequest request, String managerId) {
		String newPassword = MD5Util.getMD5(request.getParameter(managerId).getBytes());
		Manager manager = new Manager();
		manager.setPassword(newPassword);
		manager.setId(managerId);
		return managerMapper.updateByPrimaryKeySelective(manager);
	}
}
