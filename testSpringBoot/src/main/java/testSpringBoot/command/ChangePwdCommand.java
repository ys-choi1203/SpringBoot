package testSpringBoot.command;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ChangePwdCommand {
	private String userId;
	@NotEmpty
	private String userPw;
	@NotEmpty
	private String newPw;
	@NotEmpty
	private String reNewPw;
	
	public boolean isNewPwToReNewPw() {
		if(newPw.equals(reNewPw)) {
			return true;
		}
		return false;
	}
}
