package com.mycompany.propertymanagement.converter;

import org.springframework.stereotype.Component;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserEntity convertDTOtoEntity(UserDTO userDTO) {
		UserEntity ue = new UserEntity();
		ue.setId(userDTO.getId());
		ue.setOwnerName(userDTO.getOwnerName());
		ue.setEmail(userDTO.getEmail());
		ue.setPhone(userDTO.getPhone());
		ue.setPassword(userDTO.getPassword());
		
		return ue;
	}
	
	public UserDTO convertEntityToDTO(UserEntity userEntity) {
		UserDTO ud = new UserDTO();
		ud.setId(userEntity.getId());
		ud.setOwnerName(userEntity.getOwnerName());
		ud.setEmail(userEntity.getEmail());
		ud.setPhone(userEntity.getPhone());
		ud.setPassword(userEntity.getPassword());
		return ud;
	}
	
}
