package orangetaxiteam.cocoman.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
	
	//Query Method
	
	//쿼리 요청 method name -> findBy로 시작
	//쿼리 결과 레코드 수 요청  method name -> countBy로 시작
	
	//메소드 이름 키워드
	//And, Or, Between, LessThan, GreaterThanEqual, Like, IsNull, In, OrderBy
	
	User findByUsername(String username);
}
