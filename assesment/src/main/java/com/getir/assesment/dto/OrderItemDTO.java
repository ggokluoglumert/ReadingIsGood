package com.getir.assesment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO extends BaseDTO {

	@JsonIgnore
	private OrderDTO order;

	private BookDTO book;

}
