package testSpringBoot.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.domain.MemberDTO;
import testSpringBoot.mapper.MemberMapper;

@Component
@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	
	public void memberDetail(String userId, Model model) throws Exception{
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		member = memberMapper.selectByMember(member).get(0);
		model.addAttribute("memberCommand", member);
	}

}
