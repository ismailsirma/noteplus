package com.noteplus.dao;
import org.springframework.stereotype.Component;

import com.noteplus.entity.UserInfo;
@Component
public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
}