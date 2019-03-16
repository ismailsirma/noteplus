package com.noteplus.dao;
import com.noteplus.entity.UserInfo;
public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
}