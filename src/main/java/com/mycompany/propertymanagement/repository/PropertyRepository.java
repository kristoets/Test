package com.mycompany.propertymanagement.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.propertymanagement.entity.PropertyEntity;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
	
	

}
