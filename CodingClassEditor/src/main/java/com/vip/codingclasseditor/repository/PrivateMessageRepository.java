package com.vip.codingclasseditor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vip.codingclasseditor.model.PrivateMessage;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long>{

	@Query(value = "SELECT new com.vip.codingclasseditor.websocket.model.PrivateMessage("
			+ "m.msgId,m.senderId,m.receiverId,m.message,m.date) "
			+ " FROM PrivateMessage m WHERE m.senderId IN (?1,?2) AND m.receiverId IN (?1,?2)")
	Page<com.vip.codingclasseditor.websocket.model.PrivateMessage> getmsgs(long sId,long rId,Pageable pageable);
}
