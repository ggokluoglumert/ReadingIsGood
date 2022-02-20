package com.getir.assesment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;

import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.entity.Customer;
import com.getir.assesment.entity.Order;
import com.getir.assesment.entity.OrderItem;
import com.getir.assesment.repository.CustomerRepository;
import com.getir.assesment.response.StatisticResponse;
import com.getir.assesment.service.CustomerService;
import com.getir.assesment.service.impl.CustomerServiceImpl;

public class CustomerServiceTest {

	private static CustomerService customerService;
	private static CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() throws Exception {
		customerRepository = mock(CustomerRepository.class);
		customerService = new CustomerServiceImpl(customerRepository);
	}

	@Test
	public void whenCustomerNoFilledThenReturnOrderSuccess() {
		Customer customer = this.fillCustomer();

		when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(customer);
		List<OrderDTO> customerDTOList = customerService.findByOrdersWithCustomerNo(customer.getCustomerNo());
		assertThat(customerDTOList.size() > 1);
	}

	@Test
	public void whenGetOrdersStatisticThenReturnTrue() {
		Customer customer = this.fillCustomer();
		when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(customer);
		List<StatisticResponse> responseList = customerService.listOrdersStatisticForACustomer(customer.getCustomerNo());		
	}
	
	private Customer fillCustomer() {

		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		Set<Order> orderList = new HashSet<Order>();
		
		Customer customer = new Customer();
		customer.setCustomerName("gizem");
		customer.setCustomerNo(200300L);
		customer.setId(1L);
		
		Book book = new Book();
		book.setBookCode("asdas121-asdas56456-asdas4655");
		book.setBookName("deneme kitap ismi");
		book.setPrice(new BigDecimal(10));
		book.setId(1L);

		Order order = new Order();
		order.setOrderCode("Adasdsaasds-2asfa-4dgdsgsfs4554-454ggh");
		order.setOrderDate(LocalDateTime.now());
		order.setId(1L);
		order.setCustomer(customer);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setBook(book);
		orderItem.setOrder(order);
		orderItem.setId(1L);
		orderItemList.add(orderItem);
		
		order.setItems(orderItemList);
		orderList.add(order);
		customer.setOrders(orderList);
		return customer;
	}

	@Test
	public void whenCustomerNoEmptyOrNullThenReturnFailed() {
		 Throwable exception = assertThrows(EntityNotFoundException.class, () -> {
				when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(null);
				customerService.findByOrdersWithCustomerNo(null);
			});
		    assertEquals("musteri yok.", exception.getMessage());
	}
	
	@Test
	public void whenCustomerIsNotExistThenReturnCreatedCustomer() {
		Customer customer = this.fillCustomer();
		when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(null);
		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
		customerService.saveCustomer(customer.toDTO());
		assertEquals(customer.getCustomerNo(), 200300);
	}
	
	@Test
	public void whenCustomerExistThenReturnExistedCustomer() {
		Customer customer = this.fillCustomer();
		when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(customer);
		customerService.saveCustomer(customer.toDTO());
		assertEquals(customer.toDTO().getCustomerName(), "gizem");
	}
}
