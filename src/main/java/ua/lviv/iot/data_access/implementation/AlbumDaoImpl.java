package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.AlbumDao;
import ua.lviv.iot.model.Album;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlbumDaoImpl implements AlbumDao {
  @Getter
  private static final AlbumDaoImpl INSTANCE = new AlbumDaoImpl();
  private static final String SELECT_ALL = "SELECT alb FROM Album alb";
  private static final String DELETE = "DELETE FROM Album alb "
      + "WHERE alb.id = :id";
  private static final String SELECT_BY_ID = "SELECT alb FROM Album alb "
      + "WHERE alb.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<Album> findAll() {
    return session.createQuery(SELECT_ALL, Album.class).getResultList();
  }

  @Override
  public Album findById(Integer id) {
    Album album = session.createQuery(SELECT_BY_ID, Album.class)
        .setParameter("id", id)
        .getSingleResult();
    return album;
  }

  @Override
  public void create(Album album) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(album);
    transaction.commit();
  }

  @Override
  public void update(Album album) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(album);
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
