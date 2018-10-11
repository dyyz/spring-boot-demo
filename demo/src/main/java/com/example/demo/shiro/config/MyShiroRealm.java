package com.example.demo.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.example.demo.shiro.Constant;
import com.example.demo.shiro.auth.UsernamePasswordCaptchaToken;
import com.example.demo.shiro.entity.SysPermission;
import com.example.demo.shiro.entity.SysRole;
import com.example.demo.shiro.entity.UserInfo;
import com.example.demo.shiro.exception.CaptchaException;
import com.example.demo.shiro.service.UserInfoService;
import com.example.demo.shiro.service.UserLoginService;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;
    
    @Resource
    private UserLoginService userLoginService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        for(SysRole role : userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p : role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
//        System.out.println(token.getCredentials());
        
        //获取用户名 密码 第二种方式
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        String username = usernamePasswordToken.getUsername();
//        String password = new String(usernamePasswordToken.getPassword());

//        UsernamePasswordCaptchaToken usernamePasswordCaptchaToken = (UsernamePasswordCaptchaToken) token;
//        String username = usernamePasswordCaptchaToken.getUsername();
        
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }
        //验证码判断
        String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(Constant.CODE_IN_SESSION);
    	String captcha = ((UsernamePasswordCaptchaToken) token).getCaptcha();
    	System.out.println("captcha: " + captcha);
//        if(!userLoginService.validatCodeNumber(exitCode, captcha)) {
//        	throw new CaptchaException("验证码错误");
//        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}