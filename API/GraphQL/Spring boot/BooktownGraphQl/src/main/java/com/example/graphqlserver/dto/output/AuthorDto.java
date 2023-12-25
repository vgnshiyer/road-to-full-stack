package com.example.graphqlserver.dto.output;

import java.util.List;

public class AuthorDto {
    int id;

    String firstName;

    String lastName;

    List<BookDto> books;

    public AuthorDto(int id, String firstName, String lastName, List<BookDto> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<BookDto> getBooks() {
        return books;
    }


    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
