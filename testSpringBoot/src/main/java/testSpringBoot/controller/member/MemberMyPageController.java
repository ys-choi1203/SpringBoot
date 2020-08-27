package testSpringBoot.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.ChangePwdCommand;
import testSpringBoot.command.MemberCommand;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.service.member.MemberDeleteService;
import testSpringBoot.service.member.MemberDetailService;
import testSpringBoot.service.member.MemberModifyService;
import testSpringBoot.service.member.PwModifyService;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {
	@Autowired
	private MemberDetailService memberDetailService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberModifyService memberModifyService;
	@Autowired
	PwModifyService pwModifyService;
	@Autowired
	MemberDeleteService memberDeleteService;
	@ModelAttribute
	ChangePwdCommand setChangePwdCommand() {
		return new ChangePwdCommand();
	}
	
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
	
	@RequestMapping(value = "memberInfoModifyPro", method = RequestMethod.POST)
	public String memberInfoModifyPro(MemberCommand memberCommand, Model model)
		 throws Exception{
		Integer i = memberModifyService.memberModify(memberCommand, model);
		if(i > 0) {
			return "redirect:/mypage/memberDetail";
		}else {
			model.addAttribute("valid_pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/memberInfoPro"; 
		}
	}
	
	@RequestMapping(value = "memberPwForm", method = RequestMethod.GET)
	public String memberPwForm() {
		return "thymeleaf/member/pwModify";
	}
	
	@RequestMapping(value = "memberPwForm", method = RequestMethod.POST)
	public String memberPwForm1(@RequestParam(value = "userPw") String userPw,
								Model model, HttpSession session) 
		throws Exception{
		return pwModifyService.execute(userPw, model, session);
	}
	
	@RequestMapping(value = "pwModifyPro", method = RequestMethod.POST)
	public String pwModifyPro(@Validated ChangePwdCommand changePwdCommand,
							BindingResult result, HttpSession session, 
							Model model) throws Exception{
		if(result.hasErrors()) {
			return "thymeleaf/member/pwModify_1"; 
		}
		return pwModifyService.changePw(changePwdCommand, session, model);
	}
	
	@RequestMapping(value = "memberUserDel", method = RequestMethod.GET)
	public String memberUserDel() {
		return "thymeleaf/member/userDeltePw";
	}
	
	@RequestMapping(value = "memberUserDelPro", method = RequestMethod.POST)
	public String memberUserDelPro(@RequestParam(value = "userPw") String userPw,
									Model model, HttpSession session) throws Exception{
		return memberDeleteService.memberInfoDelete(model, session, userPw);
	}
}
