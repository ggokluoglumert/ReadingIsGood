package com.getir.assesment.entity;

import static javax.persistence.FetchType.LAZY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.dto.OrderItemDTO;
import com.getir.assesment.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "order_info")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity{

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	private LocalDateTime orderDate;
	private String orderCode;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "order",fetch = LAZY)
	private List<OrderItem> items = new ArrayList<>();
	
	public static Order fromDTOForOrder(OrderDTO orderDTO) {
		Order order = new Order();
		order.setOrderCode(orderDTO.getOrderCode());
		order.setVersion((long) 1);
		order.setOrderDate(orderDTO.getOrderDate());
		order.setStatus(OrderStatus.DELIVERED);
		return order;
	}
	
	public static Order fromDTO(OrderDTO order) {
		// TODO Auto-generated method stub
		return modelMapper.map(order, Order.class);
	}

	public OrderDTO toDTO() {
		// TODO Auto-generated method stub
		return modelMapper.map(this, OrderDTO.class);
	}

}
