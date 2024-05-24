package com.vip.codingclasseditor.repository;



import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.Master;
import com.vip.codingclasseditor.projection.UserProjection;

public interface MasterRepository extends UserBaseRepository<Master> {

	@Query(value = "SELECT new com.vip.codingclasseditor.projection.UserProjection(u.userId,"
			+ "u.firstName,u.lastName,u.email,u.userName,"
			+ "u.password,u.role)"
			+ "FROM User u WHERE u.userName = ?1")
	UserProjection findByUsername(String username);
	
	
}
