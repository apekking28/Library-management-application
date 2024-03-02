package com.ilham.libraryapp.model.response;

import com.ilham.libraryapp.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ListBook {
    private List<Book> borrowedBooks;
    private List<Book> unBorrowedBooks;
    private Long countBorrowedBooks;
    private Long countUnBorrowedBooks;
}
