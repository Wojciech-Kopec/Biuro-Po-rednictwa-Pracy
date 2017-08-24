package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {

    /* Persist newInstance object into Database */
    boolean create(T newInstance);

    /* Retrieve an object using pointed param */
    T read(String columnName, Serializable value);

    /* Update persisted object */
    boolean update(T object);

    /* Remove an object from the Database */
    boolean delete(T object);

    /* Retrieve all objects of type T */
    List<T> listAll();

    /* Retrieve all object based on parameterized attribute */
    List<T> listAllByAttribute(String columnName, Serializable value);
}
