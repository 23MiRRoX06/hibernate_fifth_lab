package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.MusicVideoDao;
import ua.lviv.iot.model.MusicVideo;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MusicVideoDaoImpl implements MusicVideoDao {
  @Getter
  private static final MusicVideoDaoImpl INSTANCE = new MusicVideoDaoImpl();
  private static final String SELECT_ALL = "SELECT mv FROM MusicVideo mv";
  private static final String DELETE = "DELETE FROM MusicVideo mv "
      + "WHERE mv.id = :id";
  private static final String SELECT_BY_ID = "SELECT mv FROM MusicVideo mv "
      + "WHERE mv.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<MusicVideo> findAll() {
    return session.createQuery(SELECT_ALL, MusicVideo.class).getResultList();
  }

  @Override
  public MusicVideo findById(Integer id) {
    MusicVideo musicVideo = session.createQuery(SELECT_BY_ID, MusicVideo.class)
        .setParameter("id", id)
        .getSingleResult();
    return musicVideo;
  }

  @Override
  public void create(MusicVideo musicVideo) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(musicVideo);
    transaction.commit();
  }

  @Override
  public void update(MusicVideo musicVideo) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(musicVideo);
    transaction.commit();
  }

  @Override
  public int delete(Integer id) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    int count = session.createQuery(DELETE).setParameter("id", id).executeUpdate();
    transaction.commit();
    return count;
  }
}
