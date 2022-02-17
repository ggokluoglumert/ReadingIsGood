package com.getir.assesment.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long version;
	
}
