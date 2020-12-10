package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.MusicVideoService;
import ua.lviv.iot.data_access.implementation.MusicVideoDaoImpl;
import ua.lviv.iot.model.MusicVideo;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MusicVideoServiceImpl implements MusicVideoService {
  @Getter
  private static final MusicVideoServiceImpl INSTANCE = new MusicVideoServiceImpl();
  private MusicVideoDaoImpl musicVideoDao = MusicVideoDaoImpl.getINSTANCE();

  @Override
  public List<MusicVideo> findAll() {
    return musicVideoDao.findAll();
  }

  @Override
  public MusicVideo findById(Integer id) {
    return musicVideoDao.findById(id);
  }

  @Override
  public void create(MusicVideo musicVideo) {
    musicVideoDao.create(musicVideo);
  }

  @Override
  public void update(MusicVideo musicVideo) {
    musicVideoDao.update(musicVideo);
  }

  @Override
  public int delete(Integer id) {
    return musicVideoDao.delete(id);
  }
}
