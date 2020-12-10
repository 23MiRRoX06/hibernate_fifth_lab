package ua.lviv.iot.business;

import java.util.List;

public interface GeneralService<T, Id> {
  List<T> findAll();

  T findById(Id id);

  void create(T entity);

  void update(T entity);

  int delete(Id id);
}
