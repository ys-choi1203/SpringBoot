package testSpringBoot.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import testSpringBoot.mapper.MemberMapper;

@Component
@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public void memberDelete(String userId) throws Exception{
		memberMapper.memberDelete(userId);
	}

}
