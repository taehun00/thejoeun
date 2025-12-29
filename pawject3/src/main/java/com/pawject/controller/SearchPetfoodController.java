package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawject.service.food.FoodService;
import com.pawject.service.food.SearchPetfoodService;

@Controller
@RequestMapping("/petfoodsearcher")
public class SearchPetfoodController {
	@Autowired SearchPetfoodService service;
	@Autowired FoodService fservice;

	@RequestMapping("/searchpetfood")
	public String searchpetfood (Model model) { 
		
	    model.addAttribute("brandlist", fservice.brandSelectAll());
	    model.addAttribute("foodlist", fservice.foodselectAll());
	    model.addAttribute("nutrientlist", fservice.nutrientSelectName());
	    model.addAttribute("rangelist", service.rangeList());
	    model.addAttribute("resultcard", service.resultcard());
	    
		return "petfoodsearcher/searchpetfood";
	}
}
