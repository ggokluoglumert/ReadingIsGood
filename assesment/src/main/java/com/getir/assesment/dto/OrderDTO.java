package com.getir.assesment.dto;

import java.time.LocalDateTime;

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
	
	private BookDTO book;
	
	private LocalDateTime orderDate;

}
