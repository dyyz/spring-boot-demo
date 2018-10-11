package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.shiro.entity.UserInfo;


@ControllerAdvice  // @ControllerAdvice项目中只能有一个
//@ResponseBody
public class MyControllerAdvice {
	
	/**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }
	
	@ExceptionHandler(value = UnauthorizedException.class)
//	@ResponseBody
    public ModelAndView unauthorizedHandler(HttpServletRequest request, Exception exception) throws Exception
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/error");
		modelAndView.addObject("message", exception.getMessage().toString());
//		return "您无此权限";
		return modelAndView;
    }
	
	@ExceptionHandler(value = org.apache.shiro.authz.UnauthenticatedException.class)
//	@ResponseBody
	public ModelAndView UnauthenticatedException(HttpServletRequest request, Exception exception) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/error");
		modelAndView.addObject("message", exception.getMessage().toString());
		UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
		System.out.println(user != null ? user.getName() : "11111s");
//		return "您无此权限";
		return modelAndView;
	}

    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception
    {
        exception.printStackTrace();
        return exception.getMessage();
    }

}
