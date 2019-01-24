package com.epam.newsportal.service;

import com.epam.newsportal.dto.Author;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorService extends UserDetailsService {
    boolean registration(final Author author);
}
