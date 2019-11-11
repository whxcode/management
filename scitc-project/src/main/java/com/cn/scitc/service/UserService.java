package com.cn.scitc.service;


import com.cn.scitc.entity.SysRole;
import com.cn.scitc.entity.SysUser;
import com.cn.scitc.web.dto.MessageDTO;
import com.cn.scitc.web.form.MessageForm;
import com.cn.scitc.web.form.RentSearch;


public interface UserService  {

    /**
     * 用户注册
     * @param username
     * @param password
     * @param nickname
     * @return
     */
    SysUser regiserByUsername(String username, String password, String nickname,String classes,String gender,String names);

    /**
     * 修改指定属性值{只能修改当前登录的账户}
     * @param profile
     * @param value
     * @return
     */
    ServiceResult modifyUserProfile(String profile, String value);


    /**
     * 保存用户信息{SysUser}
     * @param user
     */

    void save(SysUser user);

    /**
     * 编辑用户信息
     * @param user
     */

    void edit(SysUser user);

    /**
     * 更新用户角色权限
     * @param role
     */
    void update(SysRole role);




    /**
     * 添加公告信息
     * @param messageForm
     * @return
     */

    ServiceResult<MessageDTO> save(MessageForm messageForm);


    ServiceResult<MessageDTO> findCompleteOne(Long id);

    ServiceMultiResult<MessageDTO> query(RentSearch rentSearch);









}
