package com.pawject.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.service.food.NaverOcrService;


@Controller
@RequestMapping("/foodboard")
public class ApiOcrController {
	
@Autowired NaverOcrService service;
    @GetMapping("/test")
    public String ocr() {
        return "foodboard/test";
    }

    @PostMapping("/test")
    public String analyzeImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        String result = service.callOcrApi(convFile);
        convFile.delete();
        model.addAttribute("result", result);

        return"foodboard/test";
    }


	
 
} 















