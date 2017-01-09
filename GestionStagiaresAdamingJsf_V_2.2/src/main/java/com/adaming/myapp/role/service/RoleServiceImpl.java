package com.adaming.myapp.role.service;

import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.role.dao.IRoleDao;

@Transactional(readOnly=true)
public class RoleServiceImpl implements IRoleService {
    
	Logger log = Logger.getLogger("RoleServiceImpl");
	
    private IRoleDao dao;
    

	public void setDao(IRoleDao dao) {
		this.dao = dao;
		log.info("<--------Role Doa Injeted-------->");
	}



	@Override
	@Transactional(readOnly=false)
	public Role saveRole(Role r, Long idUser) {
		// TODO Auto-generated method stub
		return dao.saveRole(r, idUser);
	}

}
