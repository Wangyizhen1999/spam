package com.hrbuedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hrbuedu.cn.dao.mapper.TextMapper;
import com.hrbuedu.cn.model.sysUser.Text;
import com.hrbuedu.cn.model.sysUser.TextExample;
import com.hrbuedu.cn.service.TextService;

@Service
public class TextServiceImpl implements TextService{

	@Resource
	TextService textService;
	@Resource
	TextMapper textMapper;
	@Override
	public List<Text> show(HttpServletRequest request) {
		TextExample textExample = new TextExample();
		return textMapper.selectByExample(textExample);
	}

}
