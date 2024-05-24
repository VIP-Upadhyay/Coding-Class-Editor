package com.vip.codingclasseditor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.SubjectClass;
import com.vip.codingclasseditor.projection.MasterProjection;
import com.vip.codingclasseditor.projection.SubjectProjection;

public interface StudentClassRepository extends JpaRepository<SubjectClass, Long> {

	boolean existsByClassNameAndSemesterAndBranch(String subject,String semester,String branch);
	
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.SubjectProjection("
			+ "s.subId,s.className,s.semester,s.branch)"
			+ " FROM SubjectClass s WHERE s.master.userName=?1")
	Page<SubjectProjection> findSubjectsForPagin(String username,Pageable pageable);
	
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.SubjectProjection("
			+ "s.subId,s.className,s.semester,s.branch)"
			+ " FROM SubjectClass s WHERE s.branch=?1 AND s.semester=?2")
	Page<SubjectProjection> findSubjectsForStudentPagin(String branch,String semester,Pageable pageable);
	
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.MasterProjection(s.master.userId,"
			+ "s.master.firstName,s.master.lastName,s.master.email,"
			+ "s.master.userName)"
			+ " FROM SubjectClass s WHERE s.subId=?1")
	MasterProjection findMasterProjection(long subId);
}
