package testSpringBoot.domain;

import java.util.List;

import lombok.Data;

@Data
public class CommentRepliesDTO {
	private CommentDTO commentDTO;
	private List<ReplyDTO> replies;
}
