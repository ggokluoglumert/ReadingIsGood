package com.getir.assesment.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.getir.assesment.dto.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
	
	private String customerName;
	private Long customerNo;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = LAZY)
	private Set<Order> orders;
	
	public static Customer fromDTO(CustomerDTO customer) {
		return modelMapper.map(customer, Customer.class);
		
	}
	
	public CustomerDTO toDTO() {
		return modelMapper.map(this, CustomerDTO.class);
	}

}
