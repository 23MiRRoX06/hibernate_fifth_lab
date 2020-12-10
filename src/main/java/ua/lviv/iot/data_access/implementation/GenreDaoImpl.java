package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.GenreDao;
import ua.lviv.iot.model.Genre;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDaoImpl implements GenreDao {
  @Getter
  private static final GenreDaoImpl INSTANCE = new GenreDaoImpl();
  private static final String SELECT_ALL = "SELECT gen FROM Genre gen";
  private static final String DELETE = "DELETE FROM Genre gen "
      + "WHERE gen.id = :id";
  private static final String SELECT_BY_ID = "SELECT gen FROM Genre gen "
      + "WHERE gen.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<Genre> findAll() {
    return session.createQuery(SELECT_ALL, Genre.class).getResultList();
  }

  @Override
  public Genre findById(Integer id) {
    Genre genre = session.createQuery(SELECT_BY_ID, Genre.class)
        .setParameter("id", id)
        .getSingleResult();
    return genre;
  }

  @Override
  public void create(Genre genre) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(genre);
    transaction.commit();
  }

  @Override
  public void update(Genre genre) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(genre);
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
