package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.dto.output.BookDto;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import com.example.graphqlserver.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.ArrayList;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @QueryMapping
    public List<BookDto> books() {
        return this.bookService.getBooks();
    }

    @QueryMapping
    public BookDto bookByISBN(@Argument("isbn") String isbn) {
        return this.bookService.getBookByISBN(isbn);
    }

    @QueryMapping
    public List<BookDto> booksByAuthorId(@Argument("authorId") int authorId) {
        return this.bookService.getBooksByAuthorId(authorId);
    }

    @MutationMapping
    public String deleteBookByISBN(@Argument("isbn") String isbn) {
        return this.bookService.deleteBookByISBN(isbn);
    }

    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput input) {
        Book newBook = this.bookService.save(input);
        if (newBook == null) return null;
        BookDto bookDto = new BookDto(newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor().getId());
        return new AddBookPayload(bookDto);
    }
}
