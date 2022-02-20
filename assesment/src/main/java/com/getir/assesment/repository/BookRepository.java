package com.getir.assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getir.assesment.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	public Book findByBookCode(String bookCode);
}
