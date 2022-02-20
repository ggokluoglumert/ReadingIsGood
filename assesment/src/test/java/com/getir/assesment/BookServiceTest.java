package com.getir.assesment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.entity.Book;
import com.getir.assesment.entity.Customer;
import com.getir.assesment.repository.BookRepository;
import com.getir.assesment.request.UpdateBookStockRequest;
import com.getir.assesment.service.BookService;
import com.getir.assesment.service.impl.BookServiceImpl;

public class BookServiceTest {

	private BookService bookService;
	private BookRepository bookRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		bookRepository = mock(BookRepository.class);
		bookService = new BookServiceImpl(bookRepository);
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
	public void whenBookIsNotExistThenReturnCreatedBook() {
		Book book = this.fillBook();
		when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(null);
		when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		bookService.saveBook(book.toDTO());
		assertEquals(book.getBookCode(), "dsadasdsa-sadasd-644erer-9e8rere98");
	}
	
	@Test
	public void whenBookExistThenReturnExistedBook() {
		Book book = this.fillBook();
		when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(book);
		bookService.saveBook(book.toDTO());
		assertEquals(book.toDTO().getBookName(), "deneme kitap ismi");
	}
	
	@Test
	public void whenBookIsExistAndIncTrueThenReturnUpdateSuccess() {
		UpdateBookStockRequest request  = new UpdateBookStockRequest();
		Book book = this.fillBook();
		when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(book);
		when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		request.setIncreaseStock(Boolean.TRUE);
		BookDTO bookDTO = bookService.updateBookStock(this.fillStockRequest(request));
		assertEquals(bookDTO.getStock(), book.getStock());
		
	}
	@Test
	public void whenBookIsExistAndIncFalseThenReturnUpdateSuccess() {
		UpdateBookStockRequest request  = new UpdateBookStockRequest();
		Book book = this.fillBook();
		when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(book);
		when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		request.setIncreaseStock(Boolean.FALSE);
		BookDTO bookDTO = bookService.updateBookStock(this.fillStockRequest(request));
		assertEquals(bookDTO.getStock(), book.getStock());
		
	}
	
	@Test
	public void whenBookIsNotExistOrNullThenReturnFailed() {
		 Throwable exception = assertThrows(EntityNotFoundException.class, () -> {
				when(bookRepository.findByBookCode(Mockito.anyString())).thenReturn(null);
				bookService.updateBookStock(this.fillStockRequest(new UpdateBookStockRequest()));
			});
		    assertEquals("kitap yok.", exception.getMessage());
	}

	private UpdateBookStockRequest fillStockRequest(UpdateBookStockRequest request) {
		request.setBookCode("dsadasdsa-sadasd-644erer-9e8rere98");
		request.setStock(10);
		return request;
	}
	
}
