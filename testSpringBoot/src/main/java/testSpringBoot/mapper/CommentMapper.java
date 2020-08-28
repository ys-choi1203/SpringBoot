package testSpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.CommentDTO;
import testSpringBoot.domain.CommentUserDTO;

@Component
@Repository(value = "testSpringBoot.mapper.CommentMapper")
public interface CommentMapper {
	
	public Integer insertComment(CommentDTO commentDTO) throws Exception;
	public List<CommentDTO> getCommentList(CommentDTO commentDTO) throws Exception;
	//public CommentUserDTO getCommentUserList(CommentDTO dto) throws Exception;
	//public CommentDTO getCommentUserList(CommentDTO dto) throws Exception;
	public int getCommentCount() throws Exception;

}
