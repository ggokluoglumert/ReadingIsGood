package com.getir.assesment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.dto.OrderDTO;
import com.getir.assesment.request.UpdateBookStockRequest;
import com.getir.assesment.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(value = "/")
	private ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO book) {
		return ResponseEntity.ok(bookService.saveBook(book));
	}

	@PutMapping(value = "/")
	private ResponseEntity<BookDTO> updateBookStock(@RequestBody UpdateBookStockRequest request){
		return ResponseEntity.ok(bookService.updateBookStock(request));
	}

}
