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
		Book book = bookRepository.findByBookCode(request.getBookCode());
		if (book != null) {
			if (request.getIncreaseStock()) {
				book.setStock(book.getStock() + request.getStock());
			} else if (book.getStock().compareTo(request.getStock()) >= 0) {
				book.setStock(book.getStock() - request.getStock());
			} else {
				throw new RuntimeException("stok sayisi 0 dan kucuk olamaz");
			}
		} else {
			throw new EntityNotFoundException("kitap yok.");
		}
		bookRepository.save(book);
		return book.toDTO();
	}
}
