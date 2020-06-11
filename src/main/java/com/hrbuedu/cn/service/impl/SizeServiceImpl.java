package com.hrbuedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hrbuedu.cn.dao.mapper.SizeMapper;
import com.hrbuedu.cn.model.sysUser.Size;
import com.hrbuedu.cn.model.sysUser.SizeExample;
import com.hrbuedu.cn.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService{
	
	@Resource
	SizeMapper sizemapper;

	@Override
	public List<Size> show(HttpServletRequest request) {
		SizeExample sizeExample = new SizeExample();
		return sizemapper.selectByExample(sizeExample);
	}

}
