package com.ilham.libraryapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookBorrowingResponse {
    private Integer book_id;
    private String title;
    private String description;
    private Boolean isBorrowed;
}
