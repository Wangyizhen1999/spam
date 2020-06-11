package com.hrbuedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrbuedu.cn.model.sysUser.Addresser;

public interface AddressderService {

	List<Addresser> count(String memberId);

	List<Addresser> show(HttpServletRequest request);


}
