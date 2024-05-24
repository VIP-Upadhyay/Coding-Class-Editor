package com.vip.codingclasseditor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.model.Student;
import com.vip.codingclasseditor.projection.StudentListProjection;
import com.vip.codingclasseditor.projection.StudentProjection;
import com.vip.codingclasseditor.repository.StudentRepository;

@Service
@Transactional
public class StudentHibernateService {

	private final StudentRepository studentRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public StudentHibernateService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	public StudentProjection saveStudent(StudentProjection userProjection) {
		Student student = userProjection.convertToStudent();
		student.setPassword(bcrypt.encode(userProjection.getPassword()));
		return new StudentProjection(studentRepository.save(student)); 
	}
	
	public StudentProjection getStudentByUsername(String username) {
		return studentRepository.findByUsername(username);
	}
	
	public boolean isDetailsExists(String rollNo,String branch,String semester) {
		return studentRepository.existsByRollNoAndBranchAndSemester(rollNo, branch, semester);
	}
	
	public List<StudentListProjection> getStudentForSubjects(String branch,String semester,int page){
		Pageable paging = PageRequest.of(page, 5,Sort.Direction.DESC,"firstName");
		return studentRepository.getStudentList(branch, semester, paging).toList();
	}
}
