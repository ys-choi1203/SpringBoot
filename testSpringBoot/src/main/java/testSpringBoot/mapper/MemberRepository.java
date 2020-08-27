package testSpringBoot.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.MemberDTO;
@Component
@Repository
public class MemberRepository {
	private final String namespace="mappers.memberJoinOkMapper";

	
	@Autowired 
	private SqlSession sqlSession;
	public Integer joinOkUpdate(MemberDTO memberDTO) {
		 String statement = namespace + ".joinOkUpdate"; 
		 return sqlSession.update(statement,memberDTO); 
	}
}
