package testSpringBoot.service.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.MemberCommand;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.mapper.MemberMapper;

@Service
@Component
public class MemberModifyService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Integer memberModify(MemberCommand memberCommand, Model model)
			throws Exception{
		MemberDTO dto = new MemberDTO();
		dto.setUserAddr(memberCommand.getUserAddr());
		dto.setUserEmail(memberCommand.getUserEmail());
		dto.setUserGender(memberCommand.getUserGender());
		dto.setUserId(memberCommand.getUserId());
		dto.setUserName(memberCommand.getUserName());
		dto.setUserPh1(memberCommand.getUserPh1());
		dto.setUserPh2(memberCommand.getUserPh2());
		
		MemberDTO member = new MemberDTO();
		member = memberMapper.selectByMember(dto).get(0);
		if(passwordEncoder.matches(memberCommand.getUserPw(), member.getUserPw())) {
			return memberMapper.memberUpdate(dto);
		}
		return 0;
	}
}
