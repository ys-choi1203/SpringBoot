package testSpringBoot.domain;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("commnet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	private Long commentNo;
	private String cuserId;
	private Timestamp regDate;
	private String commentSubject;
	private String commentContent;
	
	private StartEndPageDTO startEndPageDTO;
	
	private List<ReplyDTO> replies;
	
	MemberDTO memberDTO;	// 1 : 1 comment에 대한 member
}
