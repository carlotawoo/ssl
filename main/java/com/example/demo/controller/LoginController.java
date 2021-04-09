package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/loginController/")
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("logintest")
    @ResponseBody
    public Object login(){
        System.out.println("访问到了****");
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("a","a");
        objectObjectHashMap.put("aaaa","aaaaa");
        return objectObjectHashMap;
    }


    @GetMapping("turn")
    public void turn(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://cn.bing.com/");
    }


    @GetMapping("getHttp")
    @ResponseBody
    public String getHttp() throws IOException {
        String DetailProject = restTemplate.exchange("http://172.168.1.179:8686/dataStandard/queryDataStandLogicCata?user=3",
                HttpMethod.GET,
                null, String.class).getBody();
        return DetailProject;
    }

}
