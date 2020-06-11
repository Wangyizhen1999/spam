package com.hrbuedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hrbuedu.cn.dao.mapper.ThemeMapper;
import com.hrbuedu.cn.model.sysUser.Theme;
import com.hrbuedu.cn.model.sysUser.ThemeExample;
import com.hrbuedu.cn.service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService{
	
	@Resource
	ThemeMapper themeMapper;
	@Resource
	ThemeService themeService;
	@Override
	
	public List<Theme> show(HttpServletRequest request) {
		ThemeExample themeExample = new ThemeExample();
		return themeMapper.selectByExample(themeExample);
	}
	

}
