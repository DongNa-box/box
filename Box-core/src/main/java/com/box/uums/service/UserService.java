package com.box.uums.service;

import com.box.framework.base.service.BaseService;
import com.box.uums.model.User;

public interface UserService extends BaseService<User,String> {

	User getUserForLogin(String username);

}
