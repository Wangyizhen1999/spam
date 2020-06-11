package com.hrbuedu.cn.controller.spam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrbuedu.cn.dao.mapper.SizeMapper;
import com.hrbuedu.cn.model.sysUser.Size;
import com.hrbuedu.cn.service.SizeService;

@RestController
@RequestMapping("size")
public class SizeController {
	@Resource
	SizeMapper sizeMapper;
	@Resource
	SizeService sizeService;
	
	@RequestMapping(value = "sizeShow", method = RequestMethod.POST)
	public List<Size> show(HttpServletRequest request){
		List<Size> showList = sizeService.show(request);
		return showList;
	}

}
