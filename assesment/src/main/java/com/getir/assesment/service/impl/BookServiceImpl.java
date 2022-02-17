package com.getir.assesment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.repository.BookRepository;
import com.getir.assesment.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookDTO saveBook(BookDTO book) {
		Book newBook = bookRepository.save(Book.fromDTO(book));
		return newBook.toDTO();
	}

}
