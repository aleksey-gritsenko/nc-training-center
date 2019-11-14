package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Entity;

import java.util.List;

public interface EntityDAO<T extends Entity> {

  void create(T entity);

  T getById(int id);

  List<T> getAllById(int id);

  void update(int id);

  void delete(int id);


}
