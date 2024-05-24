package com.vip.codingclasseditor.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.repository.PrivateMessageRepository;
import com.vip.codingclasseditor.websocket.model.PrivateMessage;

@Service
@Transactional
public class MessageHibernateService {

	private final PrivateMessageRepository repository;

	public MessageHibernateService(PrivateMessageRepository repository) {
		super();
		this.repository = repository;
	}
	
	public void saveMessage(PrivateMessage privateMessage) {
		repository.save(privateMessage.convertToPrivateMessage());
	}
	public List<PrivateMessage> getMessages(long sId,long rId,int page) {
		Pageable paging = PageRequest.of(page, 10,Sort.Direction.ASC,"date");
		return repository.getmsgs(sId, rId, paging).toList();
	}
}
