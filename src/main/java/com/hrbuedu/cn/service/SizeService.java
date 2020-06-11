package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrbuedu.cn.model.sysUser.Size;

public interface SizeService {

	List<Size> show(HttpServletRequest request);

}
