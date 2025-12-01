package com.pawject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawject.dao.food.BrandDao;
import com.pawject.dto.food.BrandDto;

@Controller
public class BrandTestController {

    @Autowired
    BrandDao brandDao;

    @RequestMapping("/brandtest")
    @ResponseBody
    public List<BrandDto> brandtest() {
        List<BrandDto> list = brandDao.brandSelectAll();
        System.out.println("brandSelectAll size = " + list.size());
        return list; // 브라우저에서 JSON으로 볼 수 있음
    }
}