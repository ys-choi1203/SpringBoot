package testSpringBoot.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO { 
	private Long replyNo;
	private String ruserId;
	private Long commentNo;
	private String cuserId;
	private Timestamp regDate;
	private String replyContent;
}
