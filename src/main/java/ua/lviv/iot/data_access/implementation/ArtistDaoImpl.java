package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.ArtistDao;
import ua.lviv.iot.model.Artist;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistDaoImpl implements ArtistDao {
  @Getter
  private static final ArtistDaoImpl INSTANCE = new ArtistDaoImpl();
  private static final String SELECT_ALL = "SELECT art FROM Artist art";
  private static final String DELETE = "DELETE FROM Artist art "
      + "WHERE art.id = :id";
  private static final String SELECT_BY_ID = "SELECT art FROM Artist art "
      + "WHERE art.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<Artist> findAll() {
    return session.createQuery(SELECT_ALL, Artist.class).getResultList();
  }

  @Override
  public Artist findById(Integer id) {
    Artist artist = session.createQuery(SELECT_BY_ID, Artist.class)
        .setParameter("id", id)
        .getSingleResult();
    return artist;
  }

  @Override
  public void create(Artist artist) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(artist);
    transaction.commit();
  }

  @Override
  public void update(Artist artist) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(artist);
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
