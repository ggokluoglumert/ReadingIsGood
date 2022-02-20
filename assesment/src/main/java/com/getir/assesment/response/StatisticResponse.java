package com.getir.assesment.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticResponse {
	
	private String month;
	private Integer totalOrderCount;
	private Integer totalBookCount;
	private BigDecimal totalPurchasedAmount;
}
