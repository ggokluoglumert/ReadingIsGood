package com.getir.assesment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO extends BaseDTO{

	private String bookName;
	
	private Integer stock;
	
	private BigDecimal price;
}
