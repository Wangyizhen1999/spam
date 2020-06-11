package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hrbuedu.cn.model.sysUser.Manager;

public interface ManagerService {

	public List<Manager> login(HttpServletRequest request);

	public List<Manager> find(HttpServletRequest request);

	public List<Manager> protect(String managerId);

	public List<Manager> findPassword(HttpServletRequest request, String managerId);

	public List<Manager> show(String managerId);

	public Manager getImg(String id);

	public int improve(Manager manager, MultipartFile file, HttpServletRequest request, String managerId,
			String mailbox) throws Exception;

	public List<Manager> update(HttpServletRequest request, String managerId);
	
	public int daUpdate(HttpServletRequest request, String managerId);

}
