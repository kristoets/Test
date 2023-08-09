package com.mycompany.propertymanagement.service;

import java.util.List;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {
	
	UserDTO register(UserDTO userDTO);
	UserDTO logij(String email, String password);

}
