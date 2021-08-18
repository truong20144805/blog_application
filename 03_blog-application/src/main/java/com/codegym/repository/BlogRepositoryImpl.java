package com.codegym.repository;

import com.codegym.model.Blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepositoryImpl implements BlogRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Blog> findAll() {
        String hql = "select b from Blog b";
        TypedQuery<Blog> query = manager.createQuery(hql, Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        String hql = "select b from Blog b where b.id = :id";
        TypedQuery<Blog> query = manager.createQuery(hql, Blog.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId() != null) {
            manager.merge(blog);
        } else {
            manager.persist(blog);
        }
    }

    @Override
    public void remove(Long id) {
        Blog blog = findById(id);
        if (blog != null) {
            manager.remove(blog);
        }
    }
}