package com.example.graphqlserver.dto.output;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.dto.output.AuthorDto;

public record AddAuthorPayload(AuthorDto author) {
}
