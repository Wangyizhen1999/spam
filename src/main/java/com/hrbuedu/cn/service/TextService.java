package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrbuedu.cn.model.sysUser.Text;

public interface TextService {

	List<Text> show(HttpServletRequest request);

}
