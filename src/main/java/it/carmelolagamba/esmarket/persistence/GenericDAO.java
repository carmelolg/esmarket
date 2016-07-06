package it.carmelolagamba.esmarket.persistence;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T>{

	public T save(T object);

	public void update(T object);

	public void remove(Long id);

	public List<T> getAll();

	public T getById(Long id);
	
	public List<T> findByCriteria(DetachedCriteria criteria);


}
