package testSpringBoot.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testSpringBoot.command.MemberCommand;
import testSpringBoot.service.member.MemberJoinService;

@Controller
@RequestMapping("register")
public class MemberController {
	@Autowired
	MemberJoinService memberJoinService;
	@ModelAttribute
	MemberCommand setMemberCommand() {
		return new MemberCommand();
	}
	
	@RequestMapping(value = "agree", method = RequestMethod.GET)
	public String agree() {
		return "thymeleaf/member/agree";
	}
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String  memberForm() {
		return "thymeleaf/member/memberForm";
	}
	@RequestMapping(value = "memberJoinAction", method = RequestMethod.POST)
	public String  memberJoin(@Validated MemberCommand memberCommand, 
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		Integer i = memberJoinService.insertMember(memberCommand,model);
		if( i == null) return "thymeleaf/member/memberForm";
		return "redirect:/";
	}
	@RequestMapping(value = "memberMail",  method = RequestMethod.GET)
	public String memberMail(@RequestParam(value = "num") String joinOk,
			@RequestParam(value = "reciver") String reciver,
			@RequestParam(value = "userId") String userId) {
		Integer i = memberJoinService.joinOkUpdate(joinOk, reciver, userId);
		if(i > 0) {
			return "thymeleaf/member/success";
		}else {
			return "thymeleaf/member/fail";
		}
	}
	
	
	
}
