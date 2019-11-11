package com.cn.scitc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cn.scitc.entity.SysRole;

/**
 * security
 * @author jswzj
 */
public interface SysRoleRepository extends JpaRepository<SysRole,Long>{
	/**
	 * 查询角色表 角色名
	 * @param name
	 * @return
	 */
	SysRole findByName(String name);

	SysRole getById(Long id);
	
}
