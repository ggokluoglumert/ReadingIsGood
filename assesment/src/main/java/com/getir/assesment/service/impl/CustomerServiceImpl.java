package com.getir.assesment.service.impl;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.entity.Customer;
import com.getir.assesment.entity.Order;
import com.getir.assesment.entity.OrderItem;
import com.getir.assesment.repository.CustomerRepository;
import com.getir.assesment.response.StatisticResponse;
import com.getir.assesment.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer isExistCustomer = customerRepository.findByCustomerNo(customerDTO.getCustomerNo());
		return isExistCustomer == null ? customerRepository.save(Customer.fromDTO(customerDTO)).toDTO()
				: isExistCustomer.toDTO();

	}

	@Override
	public List<OrderDTO> findByOrdersWithCustomerNo(Long customerNo) {
		Customer customer = customerRepository.findByCustomerNo(customerNo);
		if (customer == null) {
			throw new EntityNotFoundException("musteri yok.");
		}
		return customer.getOrders().stream().map(Order::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<StatisticResponse> listOrdersStatisticForACustomer(Long customerNo) {
		List<StatisticResponse> response = new ArrayList<StatisticResponse>();
		Customer customer = customerRepository.findByCustomerNo(customerNo);
		if (customer == null) {
			throw new EntityNotFoundException("musteri yok.");
		}
		Map<YearMonth, List<Order>> groupingOrderByDate = customer.getOrders().stream()
				.collect(Collectors.groupingBy(order -> YearMonth.from(order.getOrderDate())));
		groupingOrderByDate.forEach((k, v) -> {
			StatisticResponse resp = new StatisticResponse();
			resp.setMonth(k.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
			resp.setTotalOrderCount(v.size());
			Supplier<Stream<List<OrderItem>>> items = () -> v.stream().map(Order::getItems);
			resp.setTotalBookCount(items.get().collect(Collectors.summingInt(List::size)));
			resp.setTotalPurchasedAmount(items.get().map(List::iterator).map(Iterator<OrderItem>::next)
					.map(OrderItem::getBook).map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
			response.add(resp);
		});
		return response;
	}

}
