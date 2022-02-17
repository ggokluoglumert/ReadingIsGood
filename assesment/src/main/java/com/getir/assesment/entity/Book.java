package com.getir.assesment.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.getir.assesment.dto.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity{

	private String bookName;
	
	private Integer stock;
	
	private BigDecimal price;

	public static Book fromDTO(BookDTO book) {
		// TODO Auto-generated method stub
		return modelMapper.map(book, Book.class);
	}

	public BookDTO toDTO() {
		// TODO Auto-generated method stub
		return modelMapper.map(this, BookDTO.class);
	}
}
