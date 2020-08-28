package testSpringBoot.service.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.CommentCommand;
import testSpringBoot.domain.CommentDTO;
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
										commentCommand.getCommentContent(), null, null);
		commentMapper.insertComment(dto);
	}

}
