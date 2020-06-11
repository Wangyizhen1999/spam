package com.hrbuedu.cn.controller.spam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrbuedu.cn.dao.mapper.TextMapper;
import com.hrbuedu.cn.model.sysUser.Text;
import com.hrbuedu.cn.service.TextService;

@RestController
@RequestMapping("text")
public class TextController {
	@Resource
	TextMapper textMapper;
	@Resource
	TextService textService;
	
	@RequestMapping(value = "textShow", method = RequestMethod.POST)
	public List<Text> show(HttpServletRequest request){
		List<Text> showList = textService.show(request);
		return showList;
	}

}
