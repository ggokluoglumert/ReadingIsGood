package com.getir.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable(required=true)Long id){
		return ResponseEntity.ok(orderService.findById(id));
	}

}
