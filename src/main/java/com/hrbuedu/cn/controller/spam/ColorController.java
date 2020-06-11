package com.hrbuedu.cn.controller.spam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrbuedu.cn.dao.mapper.ColorMapper;
import com.hrbuedu.cn.model.sysUser.Color;
import com.hrbuedu.cn.service.ColorService;

@RestController
@RequestMapping("color")
public class ColorController {
	@Resource
	ColorMapper colorMapper;
	@Resource
	ColorService colorService;
	
	@RequestMapping(value = "colorShow", method = RequestMethod.POST)
	public List<Color> show(HttpServletRequest request){
		List<Color> showList = colorService.show(request);
		return showList;
	}

}
