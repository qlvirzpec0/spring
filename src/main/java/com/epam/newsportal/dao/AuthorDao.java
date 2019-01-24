package com.epam.newsportal.dao;

import com.epam.newsportal.dto.Author;

import javax.validation.constraints.NotNull;

public interface AuthorDao extends AbstractDao<Author>{
    Author findAuthorByUsername(@NotNull final String username);
}
