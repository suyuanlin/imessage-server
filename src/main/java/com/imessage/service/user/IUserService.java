package com.imessage.service.user;

import com.imessage.model.User;

public interface IUserService {
	User getUserByUserId(String userId);

	int saveUser(User user);
}
