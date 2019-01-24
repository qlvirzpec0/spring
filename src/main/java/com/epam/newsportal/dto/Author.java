package com.epam.newsportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NamedQuery(name = "authorByUsername",
        query = "SELECT a FROM Author a WHERE a.username = :username")

@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "AUTHOR_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME", length = 32, nullable = false, unique = true)
    @Pattern(regexp = "[a-bA-z0-9]{5,32}", message = "incorrect username")
    private String username;

    @Column(name = "PASSWORD", length = 96, nullable = false)
    @Size(min = 6, max = 96, message = "incorrect password")
    @JsonIgnore
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
