package com.example.graphqlserver.services;

import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.BookDto;

import java.util.List;

public interface BookService {

    // get books
    List<BookDto> getBooks();

    // get book by isbn
    BookDto getBookByISBN(String isbn);

    // save book
    Book save(AddBookInput input);

    // get books by author id
    List<BookDto> getBooksByAuthorId(int id);
    
    // delete book by ISBN
    String deleteBookByISBN(String isbn);

}