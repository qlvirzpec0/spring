package com.epam.newsportal.dao.impl.hibernate;

import com.epam.newsportal.dao.AuthorDao;
import com.epam.newsportal.dto.Author;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("authorDao")
public class AuthorDaoImpl extends BaseDao<Author> implements AuthorDao {

    @Override
    public Author findAuthorByUsername(final String username) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createNamedQuery("authorByUsername")
                .setParameter("username", username);
            return (Author) query.getSingleResult();

    }
}