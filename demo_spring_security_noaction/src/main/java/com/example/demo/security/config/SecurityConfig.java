package com.example.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.security.authentication.MyAuthenticationFailureHandler;
import com.example.demo.security.authentication.MyAuthenticationSuccessHandler;
import com.example.demo.user.service.UserLoginServiceImpl;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	MyAuthenticationFailureHandler myAuthenticationFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.headers()
//		.and()
//		.httpBasic()
//			.and()
//		.csrf()
//			.disable()
		.csrf()		//跨站请求访问保护
			.csrfTokenRepository(new CookieCsrfTokenRepository())
//			.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
			.ignoringAntMatchers("/user/**")
			.and()
			.authorizeRequests()
//				.antMatchers("/registry").permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")		// 登录时跳转的页面, 通过HTTP GET进入
				.loginProcessingUrl("/login") // 登录时处理请求页面，仅仅处理HTTP POST
				.failureUrl("/login?error")	// 表示登录出错的页面
//				.defaultSuccessUrl("/personal_center", true)	// 登陆成功跳转页面
//				.successHandler(myAuthenticationSuccessHandler) // 自定义登录成功处理
//				.failureHandler(myAuthenticationFailureHandler) // 自定义登录失败处理
				.permitAll()				// 授权所有用户
				.and()
			.logout()								// 提供注销支持
				.logoutSuccessUrl("/login?logout") // 注销后重定向到的URL
				.deleteCookies("JSESSIONID")		// 允许指定在注销成功时删除的cookie的名称
//				.deleteCookies("XSRF-TOKEN")
				.invalidateHttpSession(true)		// 指定HttpSession在注销时是否使其无效
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.permitAll()
				.and()
			.rememberMe()
				.rememberMeParameter("remember")
				.tokenValiditySeconds(1209600)
				.and()
			.sessionManagement()
//				.sessionAuthenticationErrorUrl("/error") // 如果你的再次登录是通过Remember-Me来完成的，那么将不会转到authentication-failure-url，而是返回未授权的错误码401给客户端，
														//	如果你还是想重定向一个指定的页面，那么你可以通过session-management的session-authentication-error-url属性来指定，同时需要指定该url为不受Spring Security管理，即通过http元素设置其secure=”none”。
				.maximumSessions(1)					// 只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
				.maxSessionsPreventsLogin(true)		// true,第二次登陆将被拒绝，同时重定向到failureUrl；false，第一次登陆失效
				.sessionRegistry(getSessionRegistry())
				.expiredUrl("/login?expired")		// 当用户尝试使用一个由于其再次登录导致session超时的session时所要跳转的页面。同时需要注意设置该URL为不需要进行认证。
				.and()
				.invalidSessionUrl("/login?invalid")	// session失效后跳转
				.and()
			.anonymous()
				.authorities("ANON")
			;
	}
	
	@Bean
    UserDetailsService detailsService() {
        return new UserLoginServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(detailsService()).passwordEncoder(passwordEncoder()); // 数据库用户
    	auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    	auth.eraseCredentials(true); //删除用户凭证
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/**
	 * 跟踪活跃的session,统计在线人数,显示在线用户
	 * @return
	 */
	@Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

	/**
	 * HttpSessionEventPublisher监听器的目的很明显，
	 * 这个过滤器首先监听session失效的事件(web容器配置的timeout、直接调用session.invalidate方法)，
	 * 然后由SessionRegistryImpl处理session失效事件，
	 * 从自己维护的集合缓存中清除已经失效的session信息以及session对应的认证实体信息，避免造成oom（OutOfMemory）。
	 * @return
	 */
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
}
