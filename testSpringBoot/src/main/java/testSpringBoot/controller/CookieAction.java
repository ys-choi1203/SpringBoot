package testSpringBoot.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import testSpringBoot.command.AuthInfo;

@Component
@Service
public class CookieAction {
	public void execute(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie [] cookies = request.getCookies();
		HttpSession session = request.getSession();
		if(cookies != null && cookies.length > 0) {
			for(Cookie c: cookies) {
				if(c.getName().equals("autoLogin")) {
					AuthInfo authInfo = 
							new AuthInfo(c.getValue(),null, null,null);
					session.setAttribute("authInfo",authInfo);
				}
				if(c.getName().startsWith("id")) {
					request.setAttribute("isId", c.getValue());
				}
				
			}
		}
	}
}
