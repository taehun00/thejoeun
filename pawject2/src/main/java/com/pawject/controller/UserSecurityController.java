package com.pawject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security/*")
public class UserSecurityController {

	@RequestMapping("/all")
	public String all() {return "/user/all";}
	
	@RequestMapping("/member")
	public String member() {return "/user/member";}
}
