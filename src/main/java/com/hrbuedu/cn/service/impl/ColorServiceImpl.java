package com.hrbuedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hrbuedu.cn.dao.mapper.ColorMapper;
import com.hrbuedu.cn.model.sysUser.Color;
import com.hrbuedu.cn.model.sysUser.ColorExample;
import com.hrbuedu.cn.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{

	@Resource
	ColorMapper colorMapper;
	@Override
	public List<Color> show(HttpServletRequest request) {
		ColorExample colorExample = new ColorExample();
		return colorMapper.selectByExample(colorExample);
	}

}
