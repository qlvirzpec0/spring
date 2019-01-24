package com.epam.newsportal.dao;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public interface AbstractDao<T extends Serializable> {
    T insert(@NotNull final T entity);

    T update(@NotNull final T entity);

    T delete(@NotNull final T entity);

    T findOneById(final long id);
}
