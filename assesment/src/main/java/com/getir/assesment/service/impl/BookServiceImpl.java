package com.getir.assesment.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.repository.BookRepository;
import com.getir.assesment.request.UpdateBookStockRequest;
import com.getir.assesment.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public BookDTO saveBook(BookDTO book) {
		Book isExistBook = bookRepository.findByBookCode(book.getBookCode());
		return isExistBook == null ? bookRepository.save(Book.fromDTO(book)).toDTO() : isExistBook.toDTO();
	}

	@Override
	@Transactional
	public BookDTO updateBookStock(UpdateBookStockRequest request) {
		Book isExistBook = bookRepository.findByBookCode(request.getBookCode());
		if(isExistBook != null) {
			if(request.getIncreaseStock()) {
			isExistBook.setStock(isExistBook.getStock() + request.getStock());
			}else {
				isExistBook.setStock(isExistBook.getStock() - request.getStock());	
			}
		}else {
			throw new EntityNotFoundException("kitap yok.");
		}
		bookRepository.save(isExistBook);
		return isExistBook.toDTO();
	}
}
