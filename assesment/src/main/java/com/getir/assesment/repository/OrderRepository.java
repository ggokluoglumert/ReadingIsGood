package com.getir.assesment.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getir.assesment.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByOrderCode(String orderCode);

	public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
