package com.getir.assesment.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
	
	RECEIVED,	//Your order has been received.
	PREPARING, //Your order is preparing.
	SHIPPED, 	//Your order has been shipped
	DELIVERED; //Your order has been delivered.
	
}
