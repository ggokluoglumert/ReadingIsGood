package com.getir.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping(value = "/")
	private ResponseEntity<BookDTO> saveBook(BookDTO book){
		return ResponseEntity.ok(bookService.saveBook(book));
	}
}
