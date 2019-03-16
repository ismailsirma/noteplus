package com.noteplus.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.noteplus.entity.UserInfo;
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	public UserInfo getActiveUser(String userName) {
		UserInfo activeUserInfo = new UserInfo();
		boolean status = true;
		List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE u.userName = ?")
				.setParameter(1, userName).getResultList();
		if(!list.isEmpty()) {
			activeUserInfo = (UserInfo)list.get(0);
		}
		return activeUserInfo;
	}
}