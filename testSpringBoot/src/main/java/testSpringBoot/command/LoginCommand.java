package testSpringBoot.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginCommand {
	@NotEmpty
	@Size(min = 1, max = 127, message = "아이디를 입력하여 주세요.")
	String userId;
	@NotEmpty
	String userPw;
	Boolean idStore;
	Boolean autoLogin;
}
