package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.GenreService;
import ua.lviv.iot.data_access.implementation.GenreDaoImpl;
import ua.lviv.iot.model.Genre;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreServiceImpl implements GenreService {
  @Getter
  private static final GenreServiceImpl INSTANCE = new GenreServiceImpl();
  private GenreDaoImpl genreDao = GenreDaoImpl.getINSTANCE();

  @Override
  public List<Genre> findAll() {
    return genreDao.findAll();
  }

  @Override
  public Genre findById(Integer id) {
    return genreDao.findById(id);
  }

  @Override
  public void create(Genre genre) {
    genreDao.create(genre);
  }

  @Override
  public void update(Genre genre) {
    genreDao.update(genre);
  }

  @Override
  public int delete(Integer id) {
    return genreDao.delete(id);
  }
}
