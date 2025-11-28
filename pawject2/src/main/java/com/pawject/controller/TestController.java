package com.pawject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test")
    public String home() {
        return "index"; // ViewResolver에 의해 /index.jsp로 매핑됨
    }

}
