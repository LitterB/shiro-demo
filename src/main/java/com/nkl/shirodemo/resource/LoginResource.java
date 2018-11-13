package com.nkl.shirodemo.resource;

import com.nkl.shirodemo.domain.Role;
import com.nkl.shirodemo.domain.User;
import com.nkl.shirodemo.service.impl.LoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/12 8:17
 */
@Controller
@RequestMapping("/sys")
public class LoginResource {
    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map map){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                map.get("username").toString(),
                map.get("password").toString()
        );
        subject.login(usernamePasswordToken);
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
        return "logout";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody Map<String,Object> map){
        User user =loginService.addUser(map);
        return "addUser is ok ! " + user;
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(@RequestBody Map<String, Object> map){
        Role role = loginService.addRole(map);
        return "addRole is ok! " + role;
    }

    /**
     * 注解的使用
     * @return
     */
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(){
        return "Create success!";
    }

}
