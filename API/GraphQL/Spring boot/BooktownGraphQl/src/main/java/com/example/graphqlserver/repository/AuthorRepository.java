package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    
    // find author by lastName
    List<Author> findByLastName(String lastName);

    // find author by firstName
    List<Author> findByFirstName(String firstName);
}