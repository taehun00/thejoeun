package com.pawject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired 
    private UserSecurityService service;

    @Autowired 
    private PetService pservice;
	
	@ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> adminList(
    		@RequestParam (value="pstartno", defaultValue="1") int pstartno
    ) {
    	Map<String, Object> result = new HashMap<>();
    	result.put("list", service.listUsers(pstartno));
    	result.put("paging", new PagingDto10( service.selectTotalCnt(), pstartno));
        return result;
    }
	
	// HTML 뷰 반환 (버튼 클릭 시 이동)
    @GetMapping("/userList")
    public String userListPage() {
        return "admin/userList"; // templates/admin/userList.html
    }

}
