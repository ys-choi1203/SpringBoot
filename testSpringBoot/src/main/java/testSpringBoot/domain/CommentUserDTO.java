package testSpringBoot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CommentUserDTO {
	CommentDTO commentDTO;
	MemberDTO memberDTO;
}
