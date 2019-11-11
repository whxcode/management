package com.cn.scitc.service.user;
import com.cn.scitc.entity.Message;
import com.cn.scitc.entity.SysRole;
import com.cn.scitc.entity.SysUser;
import com.cn.scitc.exception.BusinessException;
import com.cn.scitc.repository.MessageReposiotry;
import com.cn.scitc.repository.SysRoleRepository;
import com.cn.scitc.repository.SysUserRepository;
import com.cn.scitc.service.ServiceMultiResult;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.utils.LoginUtil;
import com.cn.scitc.utils.MD5Util;
import com.cn.scitc.web.dto.MessageDTO;
import com.cn.scitc.web.form.MessageForm;
import com.cn.scitc.web.form.RentSearch;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.cn.scitc.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserRepository UserRepository;
	@Autowired
	private SysRoleRepository roleRepository;

	@Autowired
	private MessageReposiotry messageReposiotry;

	@Autowired
	private ModelMapper modelMapper;

	private Logger logger = LoggerFactory.getLogger(getClass());


	@Transactional
	@Override
	public SysUser regiserByUsername(String username, String password, String nickname,String classes,String gender,String names) {
		if (UserRepository.findByUsername(username) !=null){
			throw new BusinessException("用户名已被注册:" +username);

		}
		//用户表
		SysUser user = new SysUser();
		user.setId(user.getId());
		user.setNickname(nickname);
		user.setUsername(username);
		user.setClasses(classes);
		user.setGender(gender);
		Date now = new Date();
		user.setCreate_time(now);
		user.setLast_login_time(now);
		user.setLast_update_time(now);
		user.setPassword(MD5Util.encode(password));
		//角色表
		SysRole role = new SysRole();
		role.setId(role.getId());
		role.setName(names);
		role = roleRepository.save(role);
		//中介表
		user.getRoles().add(role);
		user= UserRepository.save(user);
		logger.info("注册成功:"+ username);

		return user;

	}

	/**
	 * 修改密码和名称
	 * @param profile
	 * @param value
	 * @return
	 */
	@Transactional
	@Override
	public ServiceResult modifyUserProfile(String profile, String value) {
      Long userId = LoginUtil.getLoginUserId();
      if (profile == null || profile.isEmpty()){
      	return  new ServiceResult(false,"属性不可以为空");
	  }

	  switch (profile){
		  case "nickname":
		  	UserRepository.updateNickname(userId,value);
		  	break;
		  case "password":
		  	UserRepository.updatePassword(userId,MD5Util.encode(value));
		  	break;
		  	default:
		  		return new ServiceResult(false,"不支持的属性");

	  }

		return ServiceResult.success();
	}


	@Override
	@Transactional
	public void save(SysUser user) {

	user.setPassword(MD5Util.encode(user.getPassword()));
	UserRepository.save(user);
	}

	@Transactional
	@Override
	public void edit(SysUser user) {
		user.setPassword(MD5Util.encode(user.getPassword()));
		UserRepository.save(user);

	}
	@Transactional
	@Override
	public void update(SysRole role) {
		roleRepository.save(role);

	}

	@Override
	public ServiceResult<MessageDTO> save(MessageForm messageForm) {
		Message message = new Message();
		modelMapper.map(messageForm,message);

		Date now = new Date();
		message.setCreateTime(now);
		message.setLastUpdateTime(now);
		messageReposiotry.save(message);

		//隐射结果
		MessageDTO messageDTO = modelMapper.map(message,MessageDTO.class);


		return new ServiceResult<MessageDTO>(true,null,messageDTO);
	}

	@Override
	public ServiceResult<MessageDTO> findCompleteOne(Long id) {
		Message message = messageReposiotry.getById(id);
		if (message == null){
			return ServiceResult.noutFound();
		}

		MessageDTO result = modelMapper.map(message,MessageDTO.class);


		return ServiceResult.of(result);
	}



	@Override
	public ServiceMultiResult<MessageDTO> query(RentSearch rentSearch) {
		Sort sort = new Sort(Sort.Direction.DESC,"lastUpdateTime");
		int page = rentSearch.getStart() / rentSearch.getSize();

		Pageable pageable = PageRequest.of(page,rentSearch.getSize(),sort);

		Page<Message> messages= messageReposiotry.findAll(pageable);

		List<MessageDTO>  messageDTOS = new ArrayList<>();

		messages.forEach(message -> {
			MessageDTO messageDTO = modelMapper.map(message,MessageDTO.class);
			messageDTOS.add(messageDTO);

		});

		return new ServiceMultiResult<>(messages.getTotalElements(),messageDTOS);
	}


}


