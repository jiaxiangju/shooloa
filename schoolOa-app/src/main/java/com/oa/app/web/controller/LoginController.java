package com.oa.app.web.controller;

import java.io.UnsupportedEncodingException;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oa.app.service.user.UserServiceImpl;
import com.oa.common.entity.User;


@Controller
public class LoginController {

	public static final String URL_INDEX = "forward:/jsp/index/index.jsp";// 信息检测成功后跳转的主页url
	public static final String URL_LOGIN = "forward:/jsp/login/login.jsp";// 信息检测失败后跳转的登录url
	public static final String MSG_ERROR = "登录名或密码错误，请重试！";
	public static final String MSG_LOCKED = "此用户已禁用。";
	public static final String MSG_UNEXISTUSER = "系统无此用户！";
	public static final String MSG_LOGINOUT = "您已退出登录";

	@Resource(name = "userService")
	private UserServiceImpl userService;

	@RequestMapping(value = "/login")
	public ModelAndView login(
			@RequestParam(value = "username") String loginName,
			@RequestParam(value = "password") String passWord,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws UnsupportedEncodingException {
		
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(passWord);
		
		// =====↓↓↓ shiro authentication ↓↓↓====
		// 1. 接受提交的当事人和证书：
		AuthenticationToken token = new UsernamePasswordToken(loginName,
				passWord);
		// 2. 获取当前Subject：
		Subject currentUser = SecurityUtils.getSubject();
		
		ModelAndView mv;
		ModelMap map = new ModelMap();
		String url = URL_LOGIN;
		try {
			
			// 3. 登录：
			currentUser.login(token);
			// 登录成功
			User userRst = userService.queryUserByUsrPwd(user);
			if(null!=userRst){
				map.put("user", userRst);
				session.setAttribute("user", userRst);
				url = URL_INDEX;
				map.put("errMsg", "登录成功");
			}else{
				map.put("errMsg", "登录名或密码错误！");
			}
		} catch (IncorrectCredentialsException ice) {
			map.put("errMsg", MSG_ERROR);
		} catch (LockedAccountException lae) {
			map.put("errMsg", MSG_LOCKED);
		} catch (UnknownAccountException uae) {
			map.put("errMsg", MSG_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errMsg", "出现异常：" + e.getMessage());
		}
		mv = new ModelAndView(url, map);
		return mv;
	}


	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		session.removeAttribute("user");
		SecurityUtils.getSubject().logout();
		ModelMap map = new ModelMap();
		map.put("errMsg", MSG_LOGINOUT);
		return new ModelAndView(URL_LOGIN, map);
	}
}
