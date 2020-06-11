package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrbuedu.cn.model.sysUser.Color;

public interface ColorService {

	List<Color> show(HttpServletRequest request);

}
