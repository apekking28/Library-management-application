package com.ilham.libraryapp.util;

import com.ilham.libraryapp.entity.Book;
import com.ilham.libraryapp.model.response.BookBorrowingResponse;
import com.ilham.libraryapp.model.response.ListBook;

import java.util.List;

public class Mapper {

    public static BookBorrowingResponse bookBorrowingResponse(Book book) {

        return BookBorrowingResponse.builder()
                .book_id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .isBorrowed(book.getIsBorrowed())
                .build();
    }

    public static ListBook bookResponse(List<Book> borrowedBooks,List<Book> unBorrowedBooks, Long  countBorrowedBooks, Long countUnBorrowedBooks) {
        return ListBook.builder()
                .borrowedBooks(borrowedBooks)
                .unBorrowedBooks(unBorrowedBooks)
                .countUnBorrowedBooks(countUnBorrowedBooks)
                .countBorrowedBooks(countBorrowedBooks)
                .build();
    }
}
