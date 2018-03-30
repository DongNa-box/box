package com.box.uums.service;

import java.util.Set;

import com.box.framework.base.service.BaseService;
import com.box.uums.model.Role;

public interface RoleService extends BaseService<Role, String> {

	Set<String> getRoleCodeSet(String username);

}
