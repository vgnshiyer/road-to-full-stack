package com.example.graphqlserver.dto.output;

public class BookDto {
    String isbn;
    
    String title;

    int authorId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public BookDto(String isbn, String title, int authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

}
