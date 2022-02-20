package com.getir.assesment.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.getir.assesment.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends BaseDTO {

	private CustomerDTO customer;
	private LocalDateTime orderDate;
	private String orderCode;
	private String status;
	private List<OrderItemDTO> items;

}
