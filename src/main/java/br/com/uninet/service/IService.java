package br.com.uninet.service;

import org.springframework.data.domain.Page;

public interface IService<T> {
	
    Page<T> findAll(int page, int size);

    Page<T> findByNomeLike(String name, int page, int size);

    T findById(Integer id);

    T save(T entity);

    T update(T entity);

    void deleteById(Integer id);

}
