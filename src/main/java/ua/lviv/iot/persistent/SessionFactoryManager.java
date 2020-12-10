package ua.lviv.iot.persistent;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionFactoryManager {

  public static SessionFactory getSessionFactory() {
    Configuration configuration = new Configuration();
    configuration.configure();
    return configuration.buildSessionFactory();
  }
}
