package com.wojciech.kopec.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.wojciech.kopec.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> type;
    protected Session session;
    protected Transaction tx;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public boolean create(T newInstance) {
        boolean result = false;
        try {
            startOperation();
            session.saveOrUpdate(newInstance);
            tx.commit();
            result = true;
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return result;
    }

    public T read(String columnName, Serializable value) {
        startOperation();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.where(cb.equal(root.get(columnName), value));
        T result = session.createQuery(cq).getSingleResult();
        tx.commit();
        return result;
    }

    /**
     * Method used for updating selected Object in DB. Set as abstract as implementing classes' entities are custom and
     * require different methods applied for this operation - different Setters are needed here.
     */
    public abstract boolean update(T object);

    public boolean delete(T object) {
        boolean result = false;
        try {
            startOperation();
            session.delete(object);
            tx.commit();
            result = true;
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return result;
    }

    public List<T> listAll() {
        List<T> results = null;
        try {
            startOperation();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(type);
            Root<T> root = cq.from(type);
            CriteriaQuery<T> cqAll = cq.select(root);
            results = session.createQuery(cqAll).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return results;
    }

    public List<T> listAllByAttribute(String columnName, Serializable value) {
        List<T> results = null;
        try {
            startOperation();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(type);
            Root<T> root = cq.from(type);
            cq.where(cb.equal(root.get(columnName), value));
            results = session.createQuery(cq).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return results;
    }

    public void handleException(Exception e) throws RuntimeException {
        HibernateUtil.rollback(tx);
        throw new RuntimeException(e);
    }

    public void closeSession() {
        HibernateUtil.closeSession(session);
    }

    protected void startOperation() throws HibernateException {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }
}
