package ua.lviv.iot.data_access.implementation;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.RecordLabelDao;
import ua.lviv.iot.model.RecordLabel;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

public class RecordLabelDaoImpl implements RecordLabelDao {
  @Getter
  private static final RecordLabelDaoImpl INSTANCE = new RecordLabelDaoImpl();
  private static final String SELECT_ALL = "SELECT rclb FROM RecordLabel rclb";
  private static final String DELETE = "DELETE FROM RecordLabel rclb "
      + "WHERE rclb.id = :id";
  private static final String SELECT_BY_ID = "SELECT rclb FROM RecordLabel rclb "
      + "WHERE rclb.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<RecordLabel> findAll() {
    return session.createQuery(SELECT_ALL, RecordLabel.class).getResultList();
  }

  @Override
  public RecordLabel findById(Integer id) {
    RecordLabel recordLabel = session.createQuery(SELECT_BY_ID, RecordLabel.class)
        .setParameter("id", id)
        .getSingleResult();
    return recordLabel;
  }

  @Override
  public void create(RecordLabel recordLabel) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(recordLabel);
    transaction.commit();
  }

  @Override
  public void update(RecordLabel recordLabel) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(recordLabel);
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
