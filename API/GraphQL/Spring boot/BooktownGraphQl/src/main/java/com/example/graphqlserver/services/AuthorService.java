package com.example.graphqlserver.services;

import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AuthorDto;
import com.example.graphqlserver.model.Author;

import java.util.List;

public interface AuthorService {
    
    // get authors
    List<AuthorDto> getAuthors();

    // get author by id
    AuthorDto getAuthorById(int id);

    // get author by lastName
    List<AuthorDto> getAuthorsByLastName(String lastName);

    // update author firstName
    String updateAuthorFirstName(int id, String firstName);

    // save author
    Author save(AddAuthorInput input);

    // get books written by author given the first name
    List<String> getBooksByAuthorFirstName(String firstName);
}
