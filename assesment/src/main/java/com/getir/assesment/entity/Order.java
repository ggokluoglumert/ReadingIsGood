package com.getir.assesment.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.getir.assesment.dto.OrderDTO;

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

	@ManyToOne
	private Customer customer;
	
	
	private LocalDateTime orderDate;
	
	
	public static Order fromDTO(OrderDTO order) {
		// TODO Auto-generated method stub
		return modelMapper.map(order, Order.class);
	}

	public OrderDTO toDTO() {
		// TODO Auto-generated method stub
		return modelMapper.map(this, OrderDTO.class);
	}
}
