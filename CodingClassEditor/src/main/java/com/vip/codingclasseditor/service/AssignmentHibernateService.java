package com.vip.codingclasseditor.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.model.SubjectClass;
import com.vip.codingclasseditor.projection.AssignmentProjection;
import com.vip.codingclasseditor.repository.AssignmentRepository;

@Service
@Transactional
public class AssignmentHibernateService {
	private final AssignmentRepository repository;

	public AssignmentHibernateService(AssignmentRepository repository) {
		super();
		this.repository = repository;
	}
	
	public boolean isAssignmentExists(String practicalName,SubjectClass subjectClass) {
		return repository.existsByAssignmentNameAndSubjectClass(practicalName, subjectClass);
	}
	public void addAssigment(AssignmentProjection practicalProjection) {
		repository.save(practicalProjection.convertToAssignment());
	}

	public void deleteAssignment(long pracId) {
		// TODO Auto-generated method stub
		repository.deleteById(pracId);
	}

	public List<AssignmentProjection> getAssignmentProjections(long subId, int page) {
		// TODO Auto-generated method stub
		Pageable paging = PageRequest.of(page, 10,Sort.Direction.DESC,"assignmentDate");
		return repository.getAssignmentList(subId, paging).toList();
	}
}
