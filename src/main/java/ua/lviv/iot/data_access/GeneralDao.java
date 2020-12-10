package ua.lviv.iot.data_access;

import java.util.List;

public interface GeneralDao<T, Id> {

  List<T> findAll();

  T findById(Id id);

  void create(T entity);

  void update(T entity);

  int delete(Id id);
}
