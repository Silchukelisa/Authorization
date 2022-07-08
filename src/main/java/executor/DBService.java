package executor;

import accounts.UserDAO;
import accounts.UserProfile;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;

public class DBService {

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getPostgreSQLConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    //настройка
    private Configuration getPostgreSQLConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserProfile.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "postgres");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }


    public long addUser(UserProfile userProfile)  {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAO dao = new UserDAO(session);
            long id = dao.insertUser(userProfile);
            transaction.commit();
            session.close();
            return id;
    }

    public UserProfile getUser(String login){
            Session session = sessionFactory.openSession();
            UserDAO dao = new UserDAO(session);
            UserProfile userProfile = dao.getUserLogin(login);
            session.close();
            return userProfile;
    }


    //создать SessionFactory
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
