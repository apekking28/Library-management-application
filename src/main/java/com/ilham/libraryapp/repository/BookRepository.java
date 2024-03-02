package com.ilham.libraryapp.repository;

import com.ilham.libraryapp.entity.Book;
import com.ilham.libraryapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByBorrower(User borrower);
    List<Book> findByIsBorrowed(boolean isBorrowed);
}
