package com.epam.newsportal.dao.impl.hibernate;

import com.epam.newsportal.dao.AbstractDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Transactional(transactionManager = "transactionManager")
public abstract class BaseDao <T extends Serializable> implements AbstractDao<T> {
    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseDao() {
        entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public T insert(@NotNull final T entity){
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public T update(@NotNull final T entity){
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return entity;
    }

    @Override
    public T delete(@NotNull final T entity){
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        return entity;
    }

    @Override
    public T findOneById(@NotNull final long id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(entityClass, id);
    }

}
