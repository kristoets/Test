package com.mycompany.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	
	
	@Override
	public UserDTO register(UserDTO userDTO) {
		
		Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(userDTO.getEmail());
		if (optionalUserEntity.isPresent()) {
			List<ErrorModel> errorModelList = new ArrayList<>();
			ErrorModel errorModel = new ErrorModel();
			errorModel.setCode("USER_EXIST");
			errorModel.setMessage("User email already exists!");
			errorModelList.add(errorModel);
			throw new BusinessException(errorModelList);
		}
		UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
		userEntity = userRepository.save(userEntity);
		userDTO = userConverter.convertEntityToDTO(userEntity);
		return userDTO;
	}

	@Override
	public UserDTO login(String email, String password) {
		UserDTO userDTO = null;
		Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndPassword(email, password);
		if (optionalUserEntity.isPresent()) {
			userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
		}
		else {
			List<ErrorModel> errorModelList = new ArrayList<>();
			ErrorModel errorModel = new ErrorModel();
			errorModel.setCode("INVALID_LOGIN");
			errorModel.setMessage("Incorrect email or password");
			errorModelList.add(errorModel);
			throw new BusinessException(errorModelList);
		}
		
		return userDTO;
	}

}
