package com.example.demo.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.shiro.Constant;
import com.example.demo.shiro.service.UserLoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Controller
public class HomeController {
	
	@Autowired
	UserLoginService userLoginService;
	
	@Autowired
	DefaultKaptcha defaultKaptcha;
	
    @RequestMapping({"/","/index"})
    public String index(Model model){
    	model.addAttribute("date", new Date());
        return"/index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        Subject subject = SecurityUtils.getSubject();
//        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        if (subject.isAuthenticated()) {
//        if (userInfo == null) {
        	return "/index";
        } else {
        	return "/login";
        }
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else if (ExcessiveAttemptsException.class.getName().equals(exception)) {
//                System.out.println("ExcessiveAttemptsException -- > 登录失败次数过多：");
//                msg = "ExcessiveAttemptsException -- > 登录失败次数过多：";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            } 
//
//        }
////        System.out.println(System.getProperty("user.dir"));
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理
//        return "/login";
    }
    
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) throws Exception{
    	System.out.println("login post");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String captcha = request.getParameter("checkcode");
    	StringBuilder msg = new StringBuilder();
    	
    	model.addAttribute("username", username);
//		model.addAttribute("password", password);

		if(userLoginService.validatLogin(username, password, captcha, msg)) {
			model.addAttribute("date", new Date());
			return "/index";
		}
		model.addAttribute("msg", msg);
		return "/login";
    	
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "/403";
    }
    
    @GetMapping("/createImage")
    @ResponseBody
    public void createImage(HttpServletResponse response, HttpSession session) throws IOException {
//        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//        Random r = new Random();
//        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
//        g.fillRect(0, 0, 80, 20);
//        //获取生成的验证码
//        String code = getNumber();
//        //绑定验证码
//        session.setAttribute(Constant.CODE_IN_SESSION, code);
//        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
//        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
//        g.drawString(code, 5, 25);
//        //设置消息头
//        response.setContentType("image/jpeg");
//        OutputStream os = response.getOutputStream();
//        ImageIO.write(image, "jpeg", os);
    	
//    	byte[] captchaChallengeAsJpeg = null;
//    	ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
    	OutputStream os = response.getOutputStream();
    	
		String createText = defaultKaptcha.createText();
    	session.setAttribute(Constant.CODE_IN_SESSION, createText);
    	BufferedImage image = defaultKaptcha.createImage(createText);
		ImageIO.write(image, "jpeg", os);
    	
//    	captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//    	response.setHeader("Cache-Control", "no-store");
//    	response.setHeader("Pragma", "no-cache");
//    	response.setDateHeader("Expires", 0);
//    	response.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream;
//		try {
//			responseOutputStream = response.getOutputStream();
//			responseOutputStream.write(captchaChallengeAsJpeg);
//	        responseOutputStream.flush();
//	        responseOutputStream.close();
//		} catch (IOException e) {
//			return;
//		}
    }
    
    public String getNumber(){
        String str = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        for(int i= 0;i<4;i++){
            int index = (int)(Math.random()*str.length());
            code+=str.charAt(index);
        }
        return code;
    }
    
}