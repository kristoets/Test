package com.mycompany.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;



@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertyConverter propertyConverter;
	
	@Override
	public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
		
		PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
		pe = propertyRepository.save(pe);
		
		PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
		return dto;
	}

	@Override
	public List<PropertyDTO> getAllProperties() {
		List<PropertyEntity> listOfPropertyEntities = (List<PropertyEntity>) propertyRepository.findAll();
		List<PropertyDTO> listOfPropertyDTOs = new ArrayList<>();
		
		for (PropertyEntity pe : listOfPropertyEntities) {
			PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
			listOfPropertyDTOs.add(dto);
		}
		return listOfPropertyDTOs;
	}

	@Override
	public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
		PropertyDTO dto = null;
		if(optEn.isPresent()) {
			PropertyEntity pe = optEn.get();
			pe.setTitle(propertyDTO.getTitle());
			pe.setAddress(propertyDTO.getAddress());
			pe.setOwnerEmail(propertyDTO.getOwnerEmail());
			pe.setOwnerName(propertyDTO.getOwnerName());
			pe.setPrice(propertyDTO.getPrice());
			pe.setDescription(propertyDTO.getDescription());
			dto = propertyConverter.convertEntityToDTO(pe);
			propertyRepository.save(pe);
		}
		return dto;
	}

	@Override
	public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
		PropertyDTO dto = null;
		if(optEn.isPresent()) {
			PropertyEntity pe = optEn.get();
			pe.setDescription(propertyDTO.getDescription());
			dto = propertyConverter.convertEntityToDTO(pe);
			propertyRepository.save(pe);
		}
		return dto;
	}

	@Override
	public void deleteProperty(Long propertyId) {
		propertyRepository.deleteById(propertyId);
	}
}
