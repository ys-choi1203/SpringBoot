package testSpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.CommentDTO;
import testSpringBoot.domain.CommentRepliesDTO;
import testSpringBoot.domain.ReplyDTO;

@Component
@Repository(value = "testSpringBoot.mapper.CommentMapper")
public interface CommentMapper {
	public Integer insertComment(CommentDTO commentDTO)throws Exception;
	public List<CommentDTO> getCommentList(CommentDTO commentDTO)throws Exception;
	public int getCommentCount() throws Exception;
	public int insertReply(ReplyDTO replyDTO) throws Exception;
	public CommentDTO getCommentReplies(CommentDTO commentDTO)
			throws Exception;
	public CommentRepliesDTO commentRepliesCollection(Long commentNo)
			throws Exception;
	public CommentDTO getcommentUser(Long commentNo) throws Exception;
}

