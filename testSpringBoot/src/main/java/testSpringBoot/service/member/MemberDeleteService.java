package testSpringBoot.service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.mapper.MemberMapper;

@Component
@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void memberDelete(String userId) throws Exception{
		memberMapper.memberDelete(userId);
	}

	public String memberInfoDelete(Model model, HttpSession session,
									String userPw) throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		member = memberMapper.selectByMember(member).get(0);
		if(passwordEncoder.matches(userPw, member.getUserPw())) {
			memberMapper.memberDelete(userId);
			return "redirect:/logout";
		}else {
			model.addAttribute("valid_pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/userDeltePw";
		}
	}
	
	
}
