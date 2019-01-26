package com.grokonez.jwtauthentication.services;


import com.grokonez.jwtauthentication.entity.Book;

import java.util.List;

public interface IBookStoreService {
	
	List<Book> getBooks();
	Book createBook(Book book);
	Book updateBook(int bookId, Book book);
	Book getBook(int bookId);
	boolean deleteBook(int bookId);

}
