package com.ilham.libraryapp.controller;

import com.ilham.libraryapp.entity.Book;
import com.ilham.libraryapp.exception.BadRequestException;
import com.ilham.libraryapp.model.request.BookRequest;
import com.ilham.libraryapp.model.response.BookBorrowingResponse;
import com.ilham.libraryapp.model.response.ListBook;
import com.ilham.libraryapp.security.service.UserDetailsImpl;
import com.ilham.libraryapp.service.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@PreAuthorize("isAuthenticated()")
public class BookController {


    @Autowired
    BookService bookService;


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public ListBook listBook() {
        try {
            return bookService.listBook();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('user')")
    public List<Book> listBookForUser() {
        try {
            return bookService.listBookForUser();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public Book createBook(@RequestBody BookRequest request) {
        try {
            return bookService.createBook(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Book updatedBook(@PathVariable("id") Integer id,@RequestBody BookRequest request) {
        try {
            return bookService.updateBookById(id, request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteBook(@PathVariable("id") Integer id) {
        try {
            return bookService.deleteBookById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PostMapping("/user/borrowBook/{id}")
    @PreAuthorize("hasAuthority('user')")
    public BookBorrowingResponse borrowBook(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return bookService.borrowBook(id, user.getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
           throw new BadRequestException(ex.getMessage());
        }
    }

    @PostMapping("/user/returnBook/{id}")
    @PreAuthorize("hasAuthority('user')")
    public BookBorrowingResponse returnBook(@PathVariable("id") Integer id,  @AuthenticationPrincipal UserDetailsImpl user) throws Exception {
        try {
            return bookService.returnBook(id, user.getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }
}
