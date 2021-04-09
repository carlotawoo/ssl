package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/LoginShiroController/")
@Slf4j
public class LoginShiroController {
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        return "login success";
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("query")
    @ResponseBody
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    @ResponseBody
    public String add() {
        return "add success!";
    }
}
