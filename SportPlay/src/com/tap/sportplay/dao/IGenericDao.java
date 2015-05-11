package com.tap.sportplay.dao;

import java.util.List;
import com.tap.sportplay.domain.BaseEntity;

public interface IGenericDao <T>{
	
	long Insert(final T entity);
	T Load(final int id, String [] fields);
	int Update(final T entity);
	int Delete(final T entity);
	List<T> GetAll(String [] fields, String where);
	long Count();
}
