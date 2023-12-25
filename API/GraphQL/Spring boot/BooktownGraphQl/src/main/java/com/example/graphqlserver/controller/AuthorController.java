package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AddAuthorPayload;
import com.example.graphqlserver.dto.output.AuthorDto;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @QueryMapping
    public List<AuthorDto> authors() {
        return this.authorService.getAuthors();
    }

    @QueryMapping
    public AuthorDto authorById(@Argument("id") int id) {
        return this.authorService.getAuthorById(id);
    }

    @QueryMapping
    public List<AuthorDto> authorsByLastName(@Argument("lastName") String lastName) {
        return this.authorService.getAuthorsByLastName(lastName);
    }

    @QueryMapping
    public List<String> bookTitlesByAuthorFirstName(@Argument("firstName") String firstName) {
        return this.authorService.getBooksByAuthorFirstName(firstName);
    }

    @MutationMapping
    public String updateAuthorFirstName(@Argument("id") int id, @Argument("firstName") String firstName) {
        return this.authorService.updateAuthorFirstName(id, firstName);
    }

    @MutationMapping
    public AddAuthorPayload addAuthor(@Argument AddAuthorInput input) {
        Author newAuthor = this.authorService.save(input);
        if (newAuthor == null) return null;
        AuthorDto authorDto = new AuthorDto(newAuthor.getId(), newAuthor.getFirstName(), newAuthor.getLastName(), null);
        return new AddAuthorPayload(authorDto);
    }
}
