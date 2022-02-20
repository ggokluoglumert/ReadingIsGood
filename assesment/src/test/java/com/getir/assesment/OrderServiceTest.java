package com.getir.assesment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.entity.Customer;
import com.getir.assesment.entity.Order;
import com.getir.assesment.entity.OrderItem;
import com.getir.assesment.repository.BookRepository;
import com.getir.assesment.repository.CustomerRepository;
import com.getir.assesment.repository.OrderRepository;
import com.getir.assesment.service.OrderService;
import com.getir.assesment.service.impl.OrderServiceImpl;

public class OrderServiceTest {

	private OrderService orderService;
	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	private BookRepository bookRepository;

	@BeforeEach
	public void setUp() throws Exception {
		orderService = mock(OrderService.class);
		orderRepository = mock(OrderRepository.class);
		customerRepository = mock(CustomerRepository.class);
		bookRepository = mock(BookRepository.class);
		orderService = new OrderServiceImpl(orderRepository, customerRepository, bookRepository);
	}

	@Test
	public void whenCheckOrderByIdAndIdIsExistThenReturnSuccess() {
		Customer customer = fillCustomer();
		when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fillOrder(customer)));
		OrderDTO order = orderService.findById(1L);
		assertThat(order != null);
	}

	@Test
	public void whenOrderIdEmptyOrNullThenReturnFailed() {
		Throwable exception = assertThrows(EntityNotFoundException.class, () -> {
			when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			orderService.findById(1L);
		});
		assertEquals("siparis yok.", exception.getMessage());
	}
	
	private Book fillBook() {
		Book book = new Book();
		book.setBookCode("dsadasdsa-sadasd-644erer-9e8rere98");
		book.setBookName("deneme kitap ismi");
		book.setPrice(new BigDecimal(10));
		book.setStock(9);
		return book;
	}
	
	@Test
	public void whenOrderIsNotExistThenReturnCreatedOrder() {
		List<Order> orderList = new ArrayList<Order>();
		Order order = this.fillOrder(this.fillCustomer());
		//orderList.add(order);
		when(orderRepository.findByOrderCode(Mockito.anyString())).thenReturn(orderList);
		when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(this.fillCustomer());
		when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(this.fillBook());
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
		orderService.createOrder(order.toDTO());
		assertEquals(order.getOrderCode(), "Adasdsaasds-2asfa-4dgdsgsfs4554-454ggh");
	}
	
	@Test
	public void whenOrderIsNotExistThenReturnNull() {
		List<Order> orderList = new ArrayList<Order>();
		Order order = this.fillOrder(this.fillCustomer());
		orderList.add(order);
		when(orderRepository.findByOrderCode(Mockito.anyString())).thenReturn(orderList);
		OrderDTO orderDTO = orderService.createOrder(order.toDTO());
		assertThat(orderDTO == null);
	}

	@Test
	public void whenStockIsEmptyOrNullThenReturnFailed() {
		Order order = this.fillOrder(this.fillCustomer());
		Book book = new Book();
		book.setStock(0);
		 Throwable exception = assertThrows(RuntimeException.class, () -> {
				when(customerRepository.findByCustomerNo(Mockito.anyLong())).thenReturn(this.fillCustomer());
				when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(book);
				orderService.createOrder(order.toDTO());
			});
		    assertEquals("stok yok.", exception.getMessage());
	}
	
	@Test
	public void whenGetOrderListForTwoDateThenReturnOrderListSucess() {
		List<Order> orderList = new ArrayList<Order>();
		Order order = this.fillOrder(this.fillCustomer());
		orderList.add(order);
		when(orderRepository.findByOrderDateBetween(Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class))).thenReturn(orderList);
		List<OrderDTO> orderDTOList = orderService.findOrdersBetweenToDate(LocalDate.of(2022, 1, 1),LocalDate.now());
		assertThat(orderDTOList != null);

	}

	@Test
	public void whenOrderListIsEmptyOrNullThenReturnFailed() {
		 Throwable exception = assertThrows(RuntimeException.class, () -> {
				when(orderRepository.findByOrderDateBetween(Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class))).thenReturn(null);
				orderService.findOrdersBetweenToDate(LocalDate.of(2022, 1, 1),LocalDate.now());
			});
		    assertEquals("siparis yok.", exception.getMessage());
	}
	
	private Customer fillCustomer() {
		Set<Order> orderList = new HashSet<Order>();
		Customer customer = new Customer();
		customer.setCustomerName("gizem");
		customer.setCustomerNo(200300L);
		customer.setId(1L);
		Order order = fillOrder(customer);
		orderList.add(order);
		customer.setOrders(orderList);
		return customer;
	}

	private Order fillOrder(Customer customer) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();

		Order order = new Order();
		order.setOrderCode("Adasdsaasds-2asfa-4dgdsgsfs4554-454ggh");
		order.setOrderDate(LocalDateTime.now());
		order.setId(1L);
		order.setCustomer(customer);
		
		Book book = new Book();
		book.setBookCode("asdas121-asdas56456-asdas4655");
		book.setBookName("deneme kitap ismi");
		book.setPrice(new BigDecimal(10));
		book.setId(1L);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setBook(book);
		orderItem.setOrder(order);
		orderItem.setId(1L);
		orderItemList.add(orderItem);

		order.setItems(orderItemList);
		return order;
	}

}
