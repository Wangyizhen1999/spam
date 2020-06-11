package com.hrbuedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hrbuedu.cn.dao.mapper.AddresserMapper;
import com.hrbuedu.cn.model.sysUser.Addresser;
import com.hrbuedu.cn.model.sysUser.AddresserExample;
import com.hrbuedu.cn.service.AddressderService;

@Service
public class AddressderServiceImpl  implements AddressderService{

	@Resource
	AddressderService addressderService;
	@Resource
	AddresserMapper addresserMapper;
	/*
	 * 分组域名统计
	 */
	@Override
	public List<Addresser> count(String memberId) {
		AddresserExample addresserExample = new AddresserExample();
		AddresserExample.Criteria criteria = addresserExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		return addresserMapper.selectByAddressderCount(addresserExample);
	}
	
	/*
	 * 直接展示
	 */
	@Override
	public List<Addresser> show(HttpServletRequest request) {
		AddresserExample addresserExample = new AddresserExample();
		return addresserMapper.selectByExample(addresserExample);
	}

}
