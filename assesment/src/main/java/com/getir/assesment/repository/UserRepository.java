package com.getir.assesment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.assesment.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

}
