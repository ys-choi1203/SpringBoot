package testSpringBoot.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.controller.PageAction;
import testSpringBoot.domain.MemberDTO;
import testSpringBoot.domain.StartEndPageDTO;
import testSpringBoot.mapper.MemberMapper;
@Component
@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	public void memberList(Model model, Integer page) {
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
		StartEndPageDTO startEndPageDTO = 
				new StartEndPageDTO(startRow,endRow);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setStartEndPageDTO(startEndPageDTO);

		List<MemberDTO> members = 
				memberMapper.selectByMember(memberDTO);
		int count = memberMapper.getMemberCount();
		
		model.addAttribute("lists", members);
		model.addAttribute("count", count);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "memberList?");
	}
}
