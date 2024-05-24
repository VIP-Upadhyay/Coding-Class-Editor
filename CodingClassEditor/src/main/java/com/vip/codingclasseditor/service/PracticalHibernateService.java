package com.vip.codingclasseditor.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.model.SubjectClass;
import com.vip.codingclasseditor.projection.PracticalProjection;
import com.vip.codingclasseditor.repository.PracticalRepository;

@Service
@Transactional
public class PracticalHibernateService {
	private final PracticalRepository repository;

	public PracticalHibernateService(PracticalRepository repository) {
		super();
		this.repository = repository;
	}
	
	public boolean isPracticalExists(String practicalName,SubjectClass subjectClass) {
		return repository.existsByPracticalNameAndSubjectClass(practicalName, subjectClass);
	}
	public void addPractical(PracticalProjection practicalProjection) {
		repository.save(practicalProjection.convertToPractical());
	}

	public void deletePractical(long pracId) {
		// TODO Auto-generated method stub
		repository.deleteById(pracId);
	}

	public List<PracticalProjection> getPracticals(long subId, int page) {
		// TODO Auto-generated method stub
		Pageable paging = PageRequest.of(page, 10,Sort.Direction.DESC,"practicalDate");
		return repository.getPracticalList(subId, paging).toList();
	}
}
