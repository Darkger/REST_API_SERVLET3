package com.eugene.crude.crude.practic.repository;

import java.util.List;

public interface Genericrepository<T, ID> {
    public T getById(ID id);

    public List<T> getAll();

    public T save(T t);

    public T update(T t);

    public void deleteById(ID id);
}
