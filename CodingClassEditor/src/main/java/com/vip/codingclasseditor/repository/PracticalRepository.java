package com.vip.codingclasseditor.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.Practical;
import com.vip.codingclasseditor.model.SubjectClass;
import com.vip.codingclasseditor.projection.PracticalProjection;

public interface PracticalRepository extends JpaRepository<Practical, Long> {
	
	boolean existsByPracticalNameAndSubjectClass(String practicalName,SubjectClass subjectClass);
	
	@Query(value = "SELECT new com.vip.codingclasseditor.projection.PracticalProjection("
			+ "p.pracId,p.practicalName,p.practicalDate,p.time)"
			+ " FROM Practical p WHERE p.subjectClass.subId=?1")
	Page<PracticalProjection> getPracticalList(long subId,Pageable pageable);
}
