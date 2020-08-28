package testSpringBoot.controller.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testSpringBoot.command.CommentCommand;
import testSpringBoot.service.comment.CommentListService;
import testSpringBoot.service.comment.CommentWriteService;

@Controller
@RequestMapping("comment")
public class CommentController {
	@Autowired
	CommentWriteService commentWriteService;
	@Autowired
	CommentListService commentListService;
	
	@RequestMapping(value = "comment_list", method = RequestMethod.GET)
	public String commentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
								Model model) throws Exception{
		commentListService.execute(model, page);
		return "comment/comment_list";
	}
	
	@RequestMapping(value = "commentBoard", method = RequestMethod.GET)
	public String commentBoard() {
		return "thymeleaf/comment/commentForm";
	}
	
	@RequestMapping(value = "commentBoard", method = RequestMethod.POST)
	public String commentWrite(CommentCommand commentCommand,
							HttpSession session) throws Exception {
		commentWriteService.commentWrite(commentCommand, session);
		return "redirect:/comment/comment_list";
	}
}
