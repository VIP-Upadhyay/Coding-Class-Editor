package com.vip.codingclasseditor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.Assignment;
import com.vip.codingclasseditor.model.SubjectClass;
import com.vip.codingclasseditor.projection.AssignmentProjection;

public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
	
	boolean existsByAssignmentNameAndSubjectClass(String assignemntName,SubjectClass subjectClass);
	
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.AssignmentProjection("
			+ "p.assignId,p.assignmentName,p.assignmentDate,p.time)"
			+ " FROM Assignment p WHERE p.subjectClass.subId=?1")
	Page<AssignmentProjection> getAssignmentList(long subId,Pageable pageable);
}
