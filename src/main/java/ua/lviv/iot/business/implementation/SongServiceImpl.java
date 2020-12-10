package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.SongService;
import ua.lviv.iot.data_access.implementation.SongDaoImpl;
import ua.lviv.iot.model.Song;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SongServiceImpl implements SongService {
  @Getter
  private static final SongServiceImpl INSTANCE = new SongServiceImpl();
  private SongDaoImpl songDao = SongDaoImpl.getINSTANCE();

  @Override
  public List<Song> findAll() {
    return songDao.findAll();
  }

  @Override
  public Song findById(Integer id) {
    return songDao.findById(id);
  }

  @Override
  public void create(Song song) {
    songDao.create(song);
  }

  @Override
  public void update(Song song) {
    songDao.update(song);
  }

  @Override
  public int delete(Integer id) {
    return songDao.delete(id);
  }
}
