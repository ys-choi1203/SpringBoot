package testSpringBoot.service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.ChangePwdCommand;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.domain.UserPwChangeDTO;
import testSpringBoot.mapper.MemberMapper;

@Component
@Service
public class PwModifyService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String execute(String userPw, Model model, HttpSession session)
			throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		member = memberMapper.selectByMember(member).get(0);
		
		if(passwordEncoder.matches(userPw, member.getUserPw())) {
			return "thymeleaf/member/pwModify_1";
		}else {
			model.addAttribute("valid_pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/pwModify";
		}
	}

	public String changePw(ChangePwdCommand changePwdCommand, 
						HttpSession session, Model model) throws Exception{
		if(!changePwdCommand.isNewPwToReNewPw()) {
			model.addAttribute("valid_reNewPw", "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "thymeleaf/member/pwModify_1";
		}
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		member = memberMapper.selectByMember(member).get(0);
		if(passwordEncoder.matches(changePwdCommand.getUserPw(), member.getUserPw())){
			UserPwChangeDTO userPwChangeDTO = new UserPwChangeDTO(userId,
				passwordEncoder.encode(changePwdCommand.getNewPw()));
			memberMapper.userPwChange(userPwChangeDTO);
			return "redirect:/mypage/memberDetail";
		}else {
			model.addAttribute("valid_pw", "현재비밀번호가 맞지 않습니다.");
			return "thymeleaf/member/pwModify_1";
		}
		
	}

}
