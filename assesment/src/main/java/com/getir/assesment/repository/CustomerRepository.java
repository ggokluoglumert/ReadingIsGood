package com.getir.assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getir.assesment.dto.CustomerDTO;
import com.getir.assesment.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findByCustomerNo(Long customerNo);

}
