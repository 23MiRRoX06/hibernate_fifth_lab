package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.data_access.CustomerDao;
import ua.lviv.iot.model.Customer;
import ua.lviv.iot.persistent.SessionFactoryManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDaoImpl implements CustomerDao {
  @Getter
  private static final CustomerDaoImpl INSTANCE = new CustomerDaoImpl();
  private static final String SELECT_ALL = "SELECT cstmr FROM Customer cstmr";
  private static final String DELETE = "DELETE FROM Customer cstmr "
      + "WHERE cstmr.id = :id";
  private static final String SELECT_BY_ID = "SELECT cstmr FROM Customer cstmr "
      + "WHERE cstmr.id = :id";
  private Session session = SessionFactoryManager.getSessionFactory().openSession();

  @Override
  public List<Customer> findAll() {
    return session.createQuery(SELECT_ALL, Customer.class).getResultList();
  }

  @Override
  public Customer findById(Integer id) {
    Customer customer = session.createQuery(SELECT_BY_ID, Customer.class)
        .setParameter("id", id)
        .getSingleResult();
    return customer;
  }

  @Override
  public void create(Customer customer) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.persist(customer);
    transaction.commit();
  }

  @Override
  public void update(Customer customer) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.merge(customer);
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
