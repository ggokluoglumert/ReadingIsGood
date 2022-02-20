package com.getir.assesment.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookStockRequest {
	
	private String bookCode;
	private Integer stock;
	private Boolean increaseStock;

}
