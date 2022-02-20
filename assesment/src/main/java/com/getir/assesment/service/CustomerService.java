package com.getir.assesment.service;

import java.util.List;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.response.StatisticResponse;

public interface CustomerService {

	public CustomerDTO saveCustomer(CustomerDTO customerDTO);

	public List<OrderDTO> findByOrdersWithCustomerNo(Long customerNo);

	public List<StatisticResponse> listOrdersStatisticForACustomer(Long customerNo);
}
