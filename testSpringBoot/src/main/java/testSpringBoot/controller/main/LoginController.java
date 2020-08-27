package testSpringBoot.controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import testSpringBoot.command.LoginCommand;
import testSpringBoot.service.login.LoginService;

@Controller
@RequestMapping(value = "/")
public class LoginController {
	@Autowired
	LoginService loginService; 
	@RequestMapping(value = "login" , method = RequestMethod.GET)
	public String form() {
		return "redirect:/";
	}
	@RequestMapping(value = "login" , method = RequestMethod.POST)
	public String submit(@Validated LoginCommand loginCommand,
			BindingResult result, HttpSession session,
			HttpServletResponse response, Model model) {
		if (result.hasErrors()) {
			return "thymeleaf/main";
		}
		String location = loginService.execute(loginCommand, session, response, model);
		return location;
	}
	@RequestMapping(value = "/main/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,
			HttpServletResponse response) {
		Cookie autoLoginCookie = new Cookie("autoLogin","");
		autoLoginCookie.setPath("/");
		autoLoginCookie.setMaxAge(0);
		response.addCookie(autoLoginCookie);
		session.invalidate();
		return "redirect:/";
	}
}
