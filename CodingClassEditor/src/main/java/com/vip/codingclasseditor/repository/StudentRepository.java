package com.vip.codingclasseditor.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.Student;
import com.vip.codingclasseditor.projection.StudentListProjection;
import com.vip.codingclasseditor.projection.StudentProjection;

public interface StudentRepository extends UserBaseRepository<Student> {
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.StudentProjection(u.userId,"
			+ "u.firstName,u.lastName,u.email,u.userName,"
			+ "u.password,u.role,u.branch,u.semester,u.rollNo)"
			+ "FROM User u WHERE u.userName = ?1")
	StudentProjection findByUsername(String username);
	
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.StudentListProjection(u.userId,"
			+ "u.firstName,u.lastName,u.userName,"
			+ "u.rollNo)"
			+ " FROM User u WHERE u.branch=?1 AND u.semester=?2")
	Page<StudentListProjection> getStudentList(String branch,String semester,Pageable pageable);
	
	boolean existsByRollNoAndBranchAndSemester(String rollNo,String branch,String semester);
}
