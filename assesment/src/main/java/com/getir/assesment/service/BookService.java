package com.getir.assesment.service;

import com.getir.assesment.dto.BookDTO;
import com.getir.assesment.request.UpdateBookStockRequest;

public interface BookService {

	public BookDTO saveBook(BookDTO book);
	public BookDTO updateBookStock(UpdateBookStockRequest request);
}
