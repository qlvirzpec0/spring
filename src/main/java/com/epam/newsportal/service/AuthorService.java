package com.epam.newsportal.service;

import com.epam.newsportal.dao.AuthorDao;
import com.epam.newsportal.dto.Author;
import com.epam.newsportal.security.AuthorPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class AuthorService implements UserDetailsService {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("isAnonymous()")
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
