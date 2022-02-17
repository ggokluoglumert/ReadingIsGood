package com.getir.assesment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Customer;
import com.getir.assesment.entity.Order;
import com.getir.assesment.repository.CustomerRepository;
import com.getir.assesment.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = customerRepository.save(Customer.fromDTO(customerDTO));
		return customer.toDTO();
	}

	@Override
	public Set<Order> findByOrdersWithCustomerId(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
		Set<Order> orders = customer.getOrders();
		return orders;
	}

}
