package com.ilham.libraryapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@Table(name = "Users")
public class User implements Serializable {
    @Id
    private String id;
    @JsonIgnore
    private String password;
    private String name;
    private String email;
    @JsonIgnore
    private String roles;
    @JsonIgnore
    private Boolean isActive;
    @OneToMany(mappedBy = "borrower")
    private List<Book> borrowedBooks;

    public User(String username) {
        this.id = username;
    }
}
