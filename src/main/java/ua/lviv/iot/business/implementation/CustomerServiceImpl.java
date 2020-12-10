package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.CustomerService;
import ua.lviv.iot.data_access.implementation.CustomerDaoImpl;
import ua.lviv.iot.model.Customer;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {
  @Getter
  private static final CustomerServiceImpl INSTANCE = new CustomerServiceImpl();
  private CustomerDaoImpl customerDao = CustomerDaoImpl.getINSTANCE();

  @Override
  public List<Customer> findAll() {
    return customerDao.findAll();
  }

  @Override
  public Customer findById(Integer id) {
    return customerDao.findById(id);
  }

  @Override
  public void create(Customer customer) {
    customerDao.create(customer);
  }

  @Override
  public void update(Customer customer) {
    customerDao.update(customer);
  }

  @Override
  public int delete(Integer id) {
    return customerDao.delete(id);
  }
}
