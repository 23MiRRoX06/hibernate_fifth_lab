package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.ArtistService;
import ua.lviv.iot.data_access.implementation.ArtistDaoImpl;
import ua.lviv.iot.model.Artist;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistServiceImpl implements ArtistService {
  @Getter
  private static final ArtistServiceImpl INSTANCE = new ArtistServiceImpl();
  private ArtistDaoImpl artistDao = ArtistDaoImpl.getINSTANCE();

  @Override
  public List<Artist> findAll() {
    return artistDao.findAll();
  }

  @Override
  public Artist findById(Integer id) {
    return artistDao.findById(id);
  }

  @Override
  public void create(Artist artist) {
    artistDao.create(artist);
  }

  @Override
  public void update(Artist artist) {
    artistDao.update(artist);
  }

  @Override
  public int delete(Integer id) {
    return artistDao.delete(id);
  }
}
