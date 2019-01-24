package com.epam.newsportal.service;

import com.epam.newsportal.dao.AuthorDao;
import com.epam.newsportal.dto.Author;
import com.epam.newsportal.security.AuthorPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.NoResultException;

public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;
    private PasswordEncoder passwordEncoder;

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registration(final Author author) {
        final Author databaseAuthor = authorDao.findAuthorByUsername(author.getUsername());
        if (databaseAuthor != null) {
            return false;
        }
        author.setPassword(
                passwordEncoder.encode(author.getPassword())
        );
        authorDao.insert(author);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Author author = authorDao.findAuthorByUsername(username);
            return new AuthorPrincipal(author);
        } catch (NoResultException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
    }
}