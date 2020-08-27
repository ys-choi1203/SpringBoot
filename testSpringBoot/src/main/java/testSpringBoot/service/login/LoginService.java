package testSpringBoot.service.login;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.LoginCommand;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.mapper.MemberMapper;
@Component
@Service
public class LoginService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	AuthInfo authInfo;
	public String execute(LoginCommand loginCommand,
			HttpSession session,
			HttpServletResponse response, Model model) {
		String location = "";
		MemberDTO member = new MemberDTO();
		member.setUserId(loginCommand.getUserId());
		List<MemberDTO> lists = memberMapper.selectByMember(member);
		System.out.println("LoginService " + member.getUserId());
		if(lists.size() == 0) {
			model.addAttribute("valid_user", "아이디가 존재하지 않습니다.");
			location =  "thymeleaf/main";
		}else {
			member = lists.get(0);
			if(passwordEncoder.matches(loginCommand.getUserPw(), 
					member.getUserPw())) {
				authInfo = new AuthInfo(member.getUserId(), 
						member.getUserEmail(), member.getUserName(),
						member.getUserPw());
				session.setAttribute("authInfo",authInfo);
				Boolean idStore = loginCommand.getIdStore();
				Boolean autologin = loginCommand.getAutoLogin();
				System.out.println("autologin : " + autologin);
				if(autologin != null && autologin == true) {
					System.out.println("shdgviusdbhgvuisde");
					Cookie cookie = new Cookie("autoLogin", authInfo.getId());
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}
				if(idStore != null && idStore == true) {
					Cookie cookie = new Cookie("idStore",authInfo.getId() );
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("idStore",authInfo.getId() );
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				location ="redirect:/"; // localhost:9090
			}else {
				model.addAttribute("valid_pw", "비밀번호가 틀립니다.");
				location =  "thymeleaf/main"; // localhost:9090/login
			}
		}
		return location;
	}
}
