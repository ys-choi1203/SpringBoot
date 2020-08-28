package testSpringBoot.command;

import lombok.Data;

@Data
public class CommentCommand {
	private String commentSubject;
	private String commentContent;
}
