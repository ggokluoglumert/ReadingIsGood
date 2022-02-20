package com.getir.assesment.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.request.UpdateBookStockRequest;
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
	
	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) {
		return ResponseEntity.ok(orderService.createOrder(order));
	}
	
	@GetMapping(value="/{startDate}/{endDate}")
	public ResponseEntity<List<OrderDTO>> listOrderBetweenTwoDate(@PathVariable(required=true) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate, @PathVariable(required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		return ResponseEntity.ok(orderService.findOrdersBetweenToDate(startDate, endDate));
	}
}
