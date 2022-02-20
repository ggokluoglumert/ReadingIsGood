package com.getir.assesment.dto;

import java.math.BigDecimal;

import com.sun.istack.NotNull;

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
	
	@NotNull
	private String bookCode;
	
	private Integer stock;
	
	private BigDecimal price;
	
	
}
