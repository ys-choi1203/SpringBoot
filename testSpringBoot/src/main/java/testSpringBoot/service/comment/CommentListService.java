package testSpringBoot.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.controller.PageAction;
import testSpringBoot.domain.CommentDTO;
import testSpringBoot.domain.CommentUserDTO;
import testSpringBoot.domain.StartEndPageDTO;
import testSpringBoot.mapper.CommentMapper;

@Service
@Component
public class CommentListService {
	@Autowired
	CommentMapper commentMapper;
	public void execute(Model model, Integer page) throws Exception{
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
		CommentDTO dto = new CommentDTO();
		dto.setStartEndPageDTO(new StartEndPageDTO(startRow, endRow));
		//List<CommentUserDTO> lists = commentMapper.getCommentUserList(dto);
		List<CommentDTO> lists = commentMapper.getCommentList(dto);
		int count = commentMapper.getCommentCount();
		
		model.addAttribute("commentDTO", lists);
		model.addAttribute("count", count);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "comment_list?");
	}

}
