package com.vip.codingclasseditor.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.projection.MasterProjection;
import com.vip.codingclasseditor.projection.SubjectClassProjection;
import com.vip.codingclasseditor.projection.SubjectProjection;
import com.vip.codingclasseditor.repository.StudentClassRepository;

@Service
@Transactional
public class SubjectClassHibernateServices {
	private final StudentClassRepository repository;

	public SubjectClassHibernateServices(StudentClassRepository repository) {
		super();
		this.repository = repository;
	}
	
	public void addClass(SubjectClassProjection projection) {
		repository.save(projection.convertSubjectClass());
	}
	
	public boolean isClassExists(String subject,String semester,String branch) {
		return repository.existsByClassNameAndSemesterAndBranch(subject, semester, branch);
	}
	
	public List<SubjectProjection> getSubjects(String username,int page){
		Pageable paging = PageRequest.of(page, 4,Sort.Direction.DESC,"subId");
		return repository.findSubjectsForPagin(username, paging).toList();
	}
	
	public List<SubjectProjection> getSubjectsForStudent(String branch,String semester,int page){
		Pageable paging = PageRequest.of(page, 4,Sort.Direction.DESC,"subId");
		return repository.findSubjectsForStudentPagin(branch, semester, paging).toList();
	}
	
	public void deleteSubjectClass(long id) {
		repository.deleteById(id);
	}
	
	public MasterProjection getMasterProjection(long id) {
		return repository.findMasterProjection(id);
	}
}
