package com.vip.codingclasseditor.repository;

import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.User;
import com.vip.codingclasseditor.projection.UserProjection;

public interface UserRepository extends UserBaseRepository<User> {
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.UserProjection(u.userId,"
			+ "u.firstName,u.lastName,u.email,u.userName,"
			+ "u.password,u.role)"
			+ "FROM User u WHERE u.userName = ?1 OR u.email = ?2")
	UserProjection findUserForAuth(String userName,String email);
	
	boolean existsByEmail(String email);
	boolean existsByUserName(String username);
}
