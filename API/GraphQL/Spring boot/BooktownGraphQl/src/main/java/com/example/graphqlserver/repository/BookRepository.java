package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.graphqlserver.model.Author;

public interface BookRepository extends JpaRepository<Book, String> {
    
    List<Book> findByAuthor(Author author);
}