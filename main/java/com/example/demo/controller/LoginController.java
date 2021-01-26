package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/login")
    @ResponseBody
    public String login(){
      return "success";
    }

    @GetMapping("/turn")
    public void turn(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://cn.bing.com/");
    }

    @GetMapping("/getHttp")
    @ResponseBody
    public String getHttp() throws IOException {
        String DetailProject = restTemplate.exchange("http://172.168.1.179:8686/dataStandard/queryDataStandLogicCata?user=3",
                HttpMethod.GET,
                null, String.class).getBody();
        return DetailProject;
    }
}
