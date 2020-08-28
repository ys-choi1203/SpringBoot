package testSpringBoot.service.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.CommentCommand;
import testSpringBoot.domain.CommentDTO;
import testSpringBoot.domain.ReplyDTO;
import testSpringBoot.mapper.CommentMapper;

@Transactional	// insert할 때 필요하다
@Component
@Service
public class CommentWriteService {
	@Autowired
	CommentMapper commentMapper;

	public void commentWrite(CommentCommand commentCommand, 
								HttpSession session) throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		CommentDTO dto = new CommentDTO(null, userId, null,
										commentCommand.getCommentSubject(),
										commentCommand.getCommentContent(), null, null, null);
		commentMapper.insertComment(dto);
	}

	public void replyInsert(Long commentNo, String replyContent, 
							String cuserId, HttpSession session) throws Exception{
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setCommentNo(commentNo);
		replyDTO.setCuserId(cuserId);
		replyDTO.setReplyContent(replyContent);
		replyDTO.setRuserId(userId);
		commentMapper.insertReply(replyDTO);
	}

	public String writeInfo(Long commentNo, Model model) throws Exception{
		String path = "";
		/*
		CommentDTO dto = commentMapper.getcommentUser(commentNo);
		model.addAttribute("comment", dto);
		return "thymeleaf/comment/writerInfo";
		*/
		return "thymeleaf/comment/writerInfo1";
	}


}
