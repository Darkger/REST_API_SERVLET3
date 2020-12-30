package com.eugene.crude.crude.practic.repository;

import java.sql.SQLException;
import java.util.List;

public interface Genericrepository<T, ID> {
   T getById(ID id) throws SQLException;

     List<T> getAll() throws SQLException;

    T save(T t);

   T update(T t);

    void deleteById(ID id);
}
