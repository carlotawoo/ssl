package com.example.demo;

import cn.hutool.json.JSON;
import netscape.javascript.JSObject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@SpringBootTest
class TransureApplicationTests {


    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {

        /*Map<String,Object> hashMap = new HashMap();
        System.out.println(hashMap.isEmpty());
        ByteSource salt = ByteSource.Util.bytes("admin");
        Object obj = new SimpleHash("MD5", "ceshi2", salt, 1024);
        System.out.println(obj);*/
        String a="wusadmin@adaaddadmin";
        String[] split = a.split("@");
        List<String> list = Arrays.asList(split);
        System.out.println(list);
       /* boolean admin = Arrays.asList(split).contains("admin");
        System.out.println(admin);
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        LocalDate now = LocalDate.now();
        System.out.println(now);
        long l = System.currentTimeMillis()/1000;
        System.out.println(l);*/
    }
}
