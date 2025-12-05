package com.pawject.dao.Disswc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
	@RequestMapping("/basic.do")
	public String basic(Model model) {
		model.addAttribute("result","Hello");
		return "basic.jsp";
	}
	
	@RequestMapping("/basic2.do")
	public String basic2(Model model) {
		model.addAttribute("result","Hello");
		return "basic";
	}

}
