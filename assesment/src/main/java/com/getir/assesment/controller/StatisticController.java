package com.getir.assesment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.assesment.response.StatisticResponse;
import com.getir.assesment.service.CustomerService;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
	

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "/{customerNo}")
	public ResponseEntity<List<StatisticResponse>> listOrdersStaticticForACustomer(
			@PathVariable(required = true) Long customerNo) {
		return ResponseEntity.ok(customerService.listOrdersStatisticForACustomer(customerNo));
	}
}
