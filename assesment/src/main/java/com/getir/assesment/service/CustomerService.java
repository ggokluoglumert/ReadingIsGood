package com.getir.assesment.service;

import java.util.Set;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Order;

public interface CustomerService {
	
	public CustomerDTO saveCustomer(CustomerDTO customerDTO);
	
	public Set<Order> findByOrdersWithCustomerId(Long id);

}
