package com.getir.assesment.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.entity.Customer;
import com.getir.assesment.entity.Order;
import com.getir.assesment.entity.OrderItem;
import com.getir.assesment.repository.BookRepository;
import com.getir.assesment.repository.CustomerRepository;
import com.getir.assesment.repository.OrderRepository;
import com.getir.assesment.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	private BookRepository bookRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,CustomerRepository customerRepository, BookRepository bookRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.bookRepository = bookRepository;
	}
	
	@Override
	public OrderDTO findById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("siparis yok."));
		return order.toDTO();
	}

	@Override
	@Transactional
	public OrderDTO createOrder(OrderDTO orderDTO) {
		String orderCode = UUID.randomUUID().toString();
		List<Order> ordersACustomer = orderRepository.findByOrderCode(orderDTO.getOrderCode());
		if (ordersACustomer.size() == 0) {
			Order newOrder = Order.fromDTOForOrder(orderDTO);
			newOrder.setOrderCode(orderCode);
			Customer cust = customerRepository.findByCustomerNo(orderDTO.getCustomer().getCustomerNo());
			newOrder.setCustomer(cust);
			
			if (!CollectionUtils.isEmpty(orderDTO.getItems())) {
				orderDTO.getItems().forEach(f -> {
					 Book book =bookRepository.findByBookCode(f.getBook().getBookCode());
					 if(book.getStock() != 0){
						 book.setStock(book.getStock() - 1);
					 }else {
						 throw new RuntimeException("stok yok.");
					 }
					newOrder.getItems()
							.add(new OrderItem(newOrder,book));
				});
			}
			newOrder.setVersion(Long.parseLong("1"));
			return orderRepository.save(newOrder).toDTO();
		} else {
			return null;
		}
	}

	@Override
	public List<OrderDTO> findOrdersBetweenToDate(LocalDate startDate, LocalDate endDate) {
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		List<Order> orderList = orderRepository.findByOrderDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.now()));
		if(orderList != null) {
		orderList.forEach(order -> {
			orderDTOList.add(order.toDTO());
		});}
		else {
			throw new RuntimeException("siparis yok.");
		}
		return orderDTOList;
	}
}
