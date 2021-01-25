package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/login")
    public String login(){

        String DetailProject = restTemplate.exchange("http://172.20.10.29:8888/api/v1/obu/findALlType9",
                HttpMethod.GET,
                null, String.class).getBody();

        return DetailProject;

    }
}
