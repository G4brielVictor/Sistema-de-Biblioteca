package service;

import entities.Book;
import enums.BookStatus;
import exceptions.BookAlreadyExistsException;
import exceptions.BookNotFoundException;
import exceptions.BookUnavailableException;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book registerBook(String title, String author, String isbn) {

        //Validation Title
        if(title.isBlank()) {
            throw new IllegalArgumentException("title cannot be empty");
        }

        //Validation Author
        if(author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }

        //Validation ISBN
        if(isbn.isBlank() || !isbn.matches("[0-9]{13}")) {
            throw new IllegalArgumentException("ISBN cannot be empty, contain only numbers and must contain 13 digits");
        }
        if(bookRepository.findBookByISBN(isbn) != null) {
            throw new BookAlreadyExistsException("Book already exists");
        }

        Book book = new Book(isbn, title, author, BookStatus.AVAILABLE);
        bookRepository.saveBook(book);

        return book;
    }

    public Book findBookByISBN(String isbn) {
        Book book = bookRepository.findBookByISBN(isbn);
        if(book == null) {
            throw new BookNotFoundException("Book not found");
        }
        return book;
    }

    public Book findBookByTitle(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if(book == null) {
            throw new BookNotFoundException("Book not found");
        }
        return book;
    }

    public List<Book> findBookByAuthor(String author) {
        List<Book> books = bookRepository.findBookByAuthor(author);

        if(books.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        return books;
    }

    public void removeBook(String isbn) {
        Book book = bookRepository.findBookByISBN(isbn);
        if(book == null) {
            throw new BookNotFoundException("Book not found");
        }

        if(!book.isAvailable()) {
            throw new BookUnavailableException("Book unavailable");
        }
        bookRepository.removeBook(book);
    }

    public List<Book> listAllBooks(){
        List<Book> books = bookRepository.listAllBooks();
        if(books.isEmpty()) {
            throw new BookNotFoundException("List of books is empty");
        }

        return books;
    }
}
