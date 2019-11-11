package com.cn.scitc.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cn.scitc.entity.SysUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * crud
 * @author jswzj
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	SysUser getById(Long id);

	/**
	 * 根据用户名查询用户
	 */

	SysUser findByUsername(String username);

	/**
	 * 查询用户密码
	 *
	 */

	SysUser findByPassword(String password);


	/**
	 * data jpa分页
	 * @param pageable
	 * @return
	 */
	Page<SysUser> findAll(Pageable pageable);



	/**
	 * 模糊查询
	 * @param username
	 * @param nickname
	 * @param pageable
	 * @return
	 */


   Page<SysUser> findByOrUsernameLikeOrNicknameLike(String username,String nickname,Pageable pageable);


	/**
	 * 修改用户名密码
	 * @param id
	 * @param password
	 */
	@Modifying
	@Query("update SysUser as user set user.password = :password where id = :id")
	void updatePassword(@Param(value = "id") Long id, @Param(value = "password") String password);

	/**
	 * 修改用户名称
	 * @param id
	 * @param nickname
	 */
	@Modifying
	@Query("update SysUser as user set user.nickname = :nickname where id = :id")
	void updateNickname(@Param(value = "id") Long id, @Param(value = "nickname") String nickname);


	/**
	 * 查询未提交留校离校信息的学生
	 * @param pageable
	 * @return
	 */
	@Query(value = " from SysUser obj where obj.id not in( from UserMessageInfo)")
	Page<SysUser> getById(Pageable pageable);


	/**
	 * 条件查询为提交留校离校信息的学生(名称,用户名,班级)
	 * @param nickname
	 * @param username
	 * @param pageable
	 * @return
	 */
	@Query(value = " from SysUser obj where obj.nickname like ?1 or obj.username like ?2 or obj.classes like ?3 and obj.id  not in( from UserMessageInfo)")
	Page<SysUser> findByNicknameLike(String nickname,String username,String classes ,Pageable pageable);



}
