package com.ilham.libraryapp.service;

import com.ilham.libraryapp.entity.Book;
import com.ilham.libraryapp.entity.User;
import com.ilham.libraryapp.exception.BadRequestException;
import com.ilham.libraryapp.model.request.BookRequest;
import com.ilham.libraryapp.model.response.BookBorrowingResponse;
import com.ilham.libraryapp.model.response.ListBook;
import com.ilham.libraryapp.repository.BookRepository;
import com.ilham.libraryapp.repository.UserRepository;
import com.ilham.libraryapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    public Book createBook(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setIsBorrowed(false);
        return bookRepository.save(book);
    }

    public ListBook listBook() {
        List<Book> borrowedBooks = bookRepository.findAll().stream()
                .filter(book -> !book.getIsBorrowed())
                .collect(Collectors.toList());

        List<Book> unBorrowedBooks = bookRepository.findAll().stream()
                .filter(book -> book.getIsBorrowed())
                .collect(Collectors.toList());

        Long countBorrowedBooks = bookRepository.findAll().stream()
                .filter(book -> book.getIsBorrowed())
                .count();

        Long countUnBorrowedBooks = bookRepository.findAll().stream()
                .filter(book -> !book.getIsBorrowed())
                .count();

        return Mapper.bookResponse(borrowedBooks, unBorrowedBooks, countBorrowedBooks, countUnBorrowedBooks);
    }

    public List<Book> listBookForUser() {
        return bookRepository.findAll().stream()
                .filter(book -> !book.getIsBorrowed())
                .collect(Collectors.toList());
    }

    public Book updateBookById(Integer id, BookRequest request) throws Exception {
        Book findBook = bookRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Book with id " + id + " Not Found"));

        findBook.setTitle(request.getTitle());
        findBook.setDescription(request.getDescription());
        return bookRepository.save(findBook);
    }

    public String deleteBookById(Integer id) throws Exception {
        Book findBook = bookRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Book  with id " + id + " Not Found"));
        bookRepository.deleteById(id);
        return "Successfully delete book with id " + id;
    }

    public BookBorrowingResponse borrowBook(Integer id, String username) throws Exception {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Book  with id " + id + " Not Found"));

        User user = userRepository.findById(username).
                orElseThrow(() -> new BadRequestException("User with id " + username + " not found"));

        if (book.getIsBorrowed()) {
            throw new BadRequestException("The book is already borrowed.");
        }

        if (user.getBorrowedBooks().size() == 0) {
            LocalDateTime returnDate = LocalDateTime.now().plusDays(7);
            book.setIsBorrowed(true);
            book.setBorrowDate(LocalDateTime.now());
            book.setReturnDate(returnDate);
            book.setBorrower(user);

            bookRepository.save(book);
        } else {
            throw new BadRequestException("You must return the book before borrowing it");
        }

        return Mapper.bookBorrowingResponse(book);
    }

    public BookBorrowingResponse returnBook(Integer id, String username) throws Exception {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Book  with id" + id + " Not Found"));

        User user = userRepository.findById(username).
                orElseThrow(() -> new BadRequestException("User with id " + username + " not found"));

        if (book.getIsBorrowed() && user.getBorrowedBooks().contains(book)) {
            LocalDateTime currentDate = LocalDateTime.now();

            book.setIsBorrowed(false);
            book.setReturnDate(null);
            book.setBorrower(null);

            bookRepository.save(book);
        } else {
            throw new BadRequestException("The book is not currently borrowed by the user.");
        }

        return Mapper.bookBorrowingResponse(book);
    }
}
