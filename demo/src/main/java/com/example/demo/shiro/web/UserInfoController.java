package com.example.demo.shiro.web;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.shiro.dao.SysPermissionDao;
import com.example.demo.shiro.entity.UserInfo;
import com.example.demo.shiro.service.UserInfoService;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@Resource
	SysPermissionDao sysPermissionDao;

    @Autowired
    private UserInfoService userInfoService;
    
    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/")
//    @RequiresPermissions("userInfo:view")//权限管理;
    @RequiresRoles(value = "admin")
    public String userInfo(Model model){
    	UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
    	model.addAttribute("name", user.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("permissions", sysPermissionDao.findAll());
    	System.out.println(user.getUsername());
        return "user/userinfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(Model model){
        return "user/userinfoadd";
    }
    
    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
//    @RequiresRoles(value = "admin")
    public String userDel(){
        return "user/userinfodel";
    }
    
    @PostMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userAdd(@Validated UserInfo userInfo, BindingResult bindingResult, Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("user", userInfo);
    		return "user/userinfoadd";
    	}
    	userInfoService.addUser(userInfo);
    	model.addAttribute("users", userInfoService.findAllUser());
    	return "user/userlist";
    }
    
    @GetMapping("/userList")
    public String userList(Model model) {
    	model.addAttribute("users", userInfoService.findAllUser());
    	return "user/userlist";
    }
}