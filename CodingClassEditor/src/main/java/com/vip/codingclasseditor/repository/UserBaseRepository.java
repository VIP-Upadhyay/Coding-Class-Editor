package com.vip.codingclasseditor.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.vip.codingclasseditor.model.User;


@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {
	T findById(long id);
}
