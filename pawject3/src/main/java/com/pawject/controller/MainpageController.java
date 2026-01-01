package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pawject.service.Disease.DiseaseService;
import com.pawject.service.exec.ExecsmartService;
import com.pawject.service.review.ReviewService;

@Controller
public class MainpageController {
 @Autowired ReviewService rservice;
 @Autowired ExecsmartService sbservice;
 @Autowired DiseaseService dservice;

 // 메인페이지
    @GetMapping("users/mainpage")
    public String mainpage(Model model) {
    	model.addAttribute("reviewlist", rservice.reviewSelectAll());
    	model.addAttribute("smartlist", sbservice.selectAllsmart());
    	model.addAttribute("dislist", dservice.selectAll(null));

    	return "users/mainpage"; 
    }
}
