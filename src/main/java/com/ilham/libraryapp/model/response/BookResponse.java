package com.ilham.libraryapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookResponse {
    private Integer book_id;
    private String title;
    private String description;
    private Boolean isBorrowed;
}
