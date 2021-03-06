package com.getir.assesment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customer) {
		return ResponseEntity.ok(customerService.saveCustomer(customer));
	}

	@GetMapping(value = "/{customerNo}")
	public ResponseEntity<List<OrderDTO>> findByOrderInfoWithCustomerNo(
			@PathVariable(required = true) Long customerNo) {
		return ResponseEntity.ok(customerService.findByOrdersWithCustomerNo(customerNo));
	}
}
