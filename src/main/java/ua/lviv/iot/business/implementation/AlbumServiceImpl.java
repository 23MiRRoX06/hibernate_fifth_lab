package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.AlbumService;
import ua.lviv.iot.data_access.implementation.AlbumDaoImpl;
import ua.lviv.iot.model.Album;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlbumServiceImpl implements AlbumService {
  @Getter
  private static final AlbumServiceImpl INSTANCE = new AlbumServiceImpl();
  private AlbumDaoImpl albumDao = AlbumDaoImpl.getINSTANCE();

  @Override
  public List<Album> findAll() {
    return albumDao.findAll();
  }

  @Override
  public Album findById(Integer id) {
    return albumDao.findById(id);
  }

  @Override
  public void create(Album album) {
    albumDao.create(album);
  }

  @Override
  public void update(Album album) {
    albumDao.update(album);
  }

  @Override
  public int delete(Integer id) {
    return albumDao.delete(id);
  }
}
