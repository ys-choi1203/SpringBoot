package testSpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.MemberDTO;
@Component
@Repository(value = "testSpringBoot.mapper")
public interface MemberMapper {
	public Integer insertMember(MemberDTO dto)throws Exception;
	public Integer joinOkUpdate(MemberDTO memberDTO) ;
	public List<MemberDTO> selectByMember(MemberDTO memberDTO);
	public Integer getMemberCount();
	public Integer memberUpdate(MemberDTO dto) throws Exception;
}
