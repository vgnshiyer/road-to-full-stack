package com.example.graphqlserver.services.implementations;

import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import com.example.graphqlserver.services.AuthorService;
import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AuthorDto;
import com.example.graphqlserver.dto.output.BookDto;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.model.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorServiceImplementation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Author author : authors) {
            List<Book> books = this.bookRepository.findByAuthor(author);
            List<BookDto> bookDtos = new ArrayList<>();
            for (Book book : books) {
                BookDto bookDto = new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor().getId());
                bookDtos.add(bookDto);
            }
            authorDtos.add(new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(),
                    bookDtos));
        }
        return authorDtos;
    }

    @Override
    public AuthorDto getAuthorById(int id) {
        Author author = this.authorRepository.findById(id).orElse(null);
        if (author == null)
            return null;

        List<Book> books = this.bookRepository.findByAuthor(author);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            BookDto bookDto = new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor().getId());
            bookDtos.add(bookDto);
        }
        AuthorDto authorDto = new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(), bookDtos);
        return authorDto;
    }

    @Override
    public List<AuthorDto> getAuthorsByLastName(String lastName) {
        List<Author> authors = this.authorRepository.findByLastName(lastName);
        if (authors == null)
            return null;

        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Author author : authors) {
            List<Book> books = this.bookRepository.findByAuthor(author);
            List<BookDto> bookDtos = new ArrayList<>();
            for (Book book : books) {
                BookDto bookDto = new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor().getId());
                bookDtos.add(bookDto);
            }
            authorDtos.add(new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(),
                    bookDtos));
        }
        return authorDtos;
    }

    @Override
    public String updateAuthorFirstName(int id, String firstName) {
        Author author = this.authorRepository.findById(id).orElse(null);
        if (author == null)
            return null;

        String oldFirstName = author.getFirstName();
        author.setFirstName(firstName);
        this.authorRepository.save(author);
        return oldFirstName;
    }

    @Override
    public List<String> getBooksByAuthorFirstName(String firstName) {
        List<Author> authors = this.authorRepository.findByFirstName(firstName);
        if (authors == null)
            return null;

        List<String> bookTitles = new ArrayList<>();
        for (Author author : authors) {
            List<Book> books = this.bookRepository.findByAuthor(author);
            for (Book book : books) {
                bookTitles.add(book.getTitle());
            }
        }
        return bookTitles;
    }

    @Override
    public Author save(AddAuthorInput input) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(input.firstName());
        newAuthor.setLastName(input.lastName());
        return this.authorRepository.save(newAuthor);
    }
}
