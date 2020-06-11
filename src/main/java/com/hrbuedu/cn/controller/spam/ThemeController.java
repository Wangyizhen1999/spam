package com.hrbuedu.cn.controller.spam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrbuedu.cn.dao.mapper.ThemeMapper;
import com.hrbuedu.cn.model.sysUser.Theme;
import com.hrbuedu.cn.service.ThemeService;

@RestController
@RequestMapping("theme")
public class ThemeController {

	@Resource
	ThemeMapper themeMapper;
	@Resource
	ThemeService themeService;
	
	@RequestMapping(value = "themeShow", method = RequestMethod.POST)
	public List<Theme> show(HttpServletRequest request){
		List<Theme> themeList = themeService.show(request);
		return themeList;
	}
	
	
}
