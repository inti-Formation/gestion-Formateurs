package com.adaming.myapp.role.service;

import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.role.dao.IRoleDao;
import com.adaming.myapp.tools.LoggerConfig;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@Transactional(readOnly=true)
public class RoleServiceImpl implements IRoleService {
    
	
	
    private IRoleDao dao;
    

	public void setDao(IRoleDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<--------Role Doa Injeted-------->");
	}



	@Override
	@Transactional(readOnly=false)
	public Role saveRole(Role r, Long idUser) {
		// TODO Auto-generated method stub
		return dao.saveRole(r, idUser);
	}

}
