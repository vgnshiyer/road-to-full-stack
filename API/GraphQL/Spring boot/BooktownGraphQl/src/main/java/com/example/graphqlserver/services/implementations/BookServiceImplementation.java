package com.example.graphqlserver.services.implementations;

import com.example.graphqlserver.services.BookService;
import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.dto.output.BookDto;
import com.example.graphqlserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class BookServiceImplementation implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    @Override
    public List<BookDto> getBooks() {
        List<Book> books = this.bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor().getId()));
        }
        return bookDtos;
    }

    @Override
    public BookDto getBookByISBN(String isbn) {
        Book book = this.bookRepository.findById(isbn).orElse(null);
        if (book == null) return null;

        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor().getId());
    }

    @Override
    public Book save(AddBookInput input) {
        Book newBook = new Book();
        newBook.setIsbn(input.isbn());
        newBook.setTitle(input.title());
        Author author = this.authorRepository.findById(input.authorId()).orElse(null);
        if (author == null) return null;

        newBook.setAuthor(author);
        return this.bookRepository.save(newBook);
    }

    @Override
    public List<BookDto> getBooksByAuthorId(int id) {
        Author author = this.authorRepository.findById(id).orElse(null);
        if (author == null) return null;
        List<Book> books = this.bookRepository.findByAuthor(author);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor().getId()));
        }
        return bookDtos;
    }

    @Override
    public String deleteBookByISBN(String isbn) {
        Book book = this.bookRepository.findById(isbn).orElse(null);
        if (book == null) return null;
        this.bookRepository.delete(book);
        return isbn;
    }
}
