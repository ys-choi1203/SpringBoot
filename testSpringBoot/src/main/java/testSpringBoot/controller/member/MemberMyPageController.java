package testSpringBoot.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.service.member.MemberDetailService;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {
	@Autowired
	private MemberDetailService memberDetailService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("memberDetail")
	public String memberDetail(Model model, HttpSession session)
			throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		memberDetailService.memberDetail(userId, model);
		return "thymeleaf/member/memberDetail";
	}
	
	@RequestMapping("memberPw")
	public String memberPw() {
		return "thymeleaf/member/memberInfoPw";
	}
	
	@RequestMapping(value = "memberInfoCng", method = RequestMethod.POST)
	public String memberInfoCng(HttpSession session, Model model,
						@RequestParam(value = "userPw") String userPw) throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		MemberDTO member = memberDetailService.memberDetail(userId, model);
		if(passwordEncoder.matches(userPw, member.getUserPw())) {
			return "thymeleaf/member/memberInfoPro";
		}else {
			model.addAttribute("valid_pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/memberInfoPw";
		}
	}
}
