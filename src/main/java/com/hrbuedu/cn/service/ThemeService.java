package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrbuedu.cn.model.sysUser.Theme;

public interface ThemeService {

	List<Theme> show(HttpServletRequest request);

}
