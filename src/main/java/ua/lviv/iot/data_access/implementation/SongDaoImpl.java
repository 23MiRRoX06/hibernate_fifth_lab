package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.SongDao;
import ua.lviv.iot.model.Song;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SongDaoImpl implements SongDao {
  @Getter
  private static final SongDaoImpl INSTANCE = new SongDaoImpl();
  private static final String SELECT_ALL = "SELECT sng FROM Song sng";
  private static final String DELETE = "DELETE FROM Song sng "
      + "WHERE sng.id = :id";
  private static final String SELECT_BY_ID = "SELECT sng FROM Song sng "
      + "WHERE sng.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<Song> findAll() {
    return session.createQuery(SELECT_ALL, Song.class).getResultList();
  }

  @Override
  public Song findById(Integer id) {
    Song song = session.createQuery(SELECT_BY_ID, Song.class)
        .setParameter("id", id)
        .getSingleResult();
    return song;
  }

  @Override
  public void create(Song song) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(song);
    transaction.commit();
  }

  @Override
  public void update(Song song) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(song);
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
