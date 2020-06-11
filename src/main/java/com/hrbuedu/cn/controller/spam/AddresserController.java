package com.hrbuedu.cn.controller.spam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrbuedu.cn.dao.mapper.AddresserMapper;
import com.hrbuedu.cn.model.sysUser.Addresser;
import com.hrbuedu.cn.service.AddressderService;

@RestController
@RequestMapping("addressder")
public class AddresserController {
	@Resource
	AddresserMapper addresserMapper;
	@Resource
	AddressderService addressderService;
	
	/*
	 * 通过分组统计
	 */
	@RequestMapping(value = "addressderCount", method = RequestMethod.POST)
	public Map<String, Object> count(HttpServletRequest request, String memberId){
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Addresser> countList = addressderService.count(memberId);
	    map.put("countList", countList);
		return map;
	}
	
	/*
	 * 直接展示
	 */
	@RequestMapping(value = "addressderShow", method = RequestMethod.POST)
	public List<Addresser> show(HttpServletRequest request){
		List<Addresser> showList = addressderService.show(request);
		return showList;
	}

}
