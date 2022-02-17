package com.getir.assesment.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Order;
import com.getir.assesment.repository.OrderRepository;
import com.getir.assesment.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderDTO findById(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
		return order.toDTO();
	}

}

