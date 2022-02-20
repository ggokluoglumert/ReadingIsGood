package com.getir.assesment.service;

import java.time.LocalDate;
import java.util.List;

import com.getir.assesment.dto.OrderDTO;

public interface OrderService {

	public OrderDTO findById(Long id);

	public OrderDTO createOrder(OrderDTO orderDTO);

	public List<OrderDTO> findOrdersBetweenToDate(LocalDate startDate, LocalDate endDate);

}
