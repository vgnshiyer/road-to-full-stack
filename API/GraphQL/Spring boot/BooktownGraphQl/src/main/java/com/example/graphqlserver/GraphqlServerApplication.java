package com.example.graphqlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.graphqlserver.services.BookService;
import com.example.graphqlserver.services.AuthorService;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.input.AddBookInput;

@SpringBootApplication
public class GraphqlServerApplication implements CommandLineRunner{

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Author author1 = new Author();
		author1.setFirstName("Robert");
		author1.setLastName("Frost");

		this.authorService.save(new AddAuthorInput(author1.getFirstName(), author1.getLastName()));

		Book book1 = new Book();
		book1.setIsbn("123456789");
		book1.setTitle("The Road Not Taken");
		book1.setAuthor(author1);

		this.bookService.save(new AddBookInput(book1.getIsbn(), book1.getTitle(), authorService.getAuthorById(1).getId()));

		Author author2 = new Author();
		author2.setFirstName("Martin");
		author2.setLastName("Fowler");

		this.authorService.save(new AddAuthorInput(author2.getFirstName(), author2.getLastName()));

		Book book2 = new Book();
		book2.setIsbn("987654321");
		book2.setTitle("To Kill a Mockingbird");
		book2.setAuthor(author2);

		this.bookService.save(new AddBookInput(book2.getIsbn(), book2.getTitle(), authorService.getAuthorById(2).getId()));

		Author author3 = new Author();
		author3.setFirstName("Kevin");
		author3.setLastName("Gary");

		this.authorService.save(new AddAuthorInput(author3.getFirstName(), author3.getLastName()));

		Book book3 = new Book();
		book3.setIsbn("456789123");
		book3.setTitle("The Great Gatsby");
		book3.setAuthor(author3);

		this.bookService.save(new AddBookInput(book3.getIsbn(), book3.getTitle(), authorService.getAuthorById(3).getId()));
	}

}
