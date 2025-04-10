package vn.iotstar.impl.service;

import vn.iotstar.entity.Role;
import vn.iotstar.impl.dao.*;
import vn.iotstar.service.IRoleService;
import vn.iotstar.dao.*;

public class RoleService implements IRoleService{
	
	public IRoleDao roleDao = new RoleDao();

	@Override
	public Role getDefaultUserRole() {
	    return roleDao.findById(1); // Hoặc tên method rõ ràng hơn
	}


}
