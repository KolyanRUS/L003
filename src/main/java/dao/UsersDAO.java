package dao;

import Entity.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import util.Util;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {
    private final SessionFactory sessionFactory;
    Configuration configuration;
    Session session;


    public UsersDAO() {//без параметров сделать, конфигурацию из утила получать
        this.configuration = Util.getInstance().getH2Configuration();
        this.sessionFactory = createSessionFactory(configuration);
}

    public User get(long id) throws HibernateException {
        session = sessionFactory.openSession();
        User dataSet = (User) session.get(User.class, id);
        session.close();
        return dataSet;
    }

    public User get_hql(long id) throws HibernateException {
        String hql = "FROM User where id = :paramName";
        Query query = session.createQuery(hql);
        query.setParameter("paramName", id);
        return (User) query.uniqueResult();
    }

    public User getUserId(String login) throws HibernateException {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        session.close();
        return (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        //Restrictions - ограничения
        //uniqueResult - уникальный результат запроса, если запрос выдаёт >1 результатов, то
            //кидается исключение NonUniqueResultException
    }

    public User getUserId_hql(String login) throws HibernateException {
        String hql = "FROM User where login = :paramName";
        Query query = session.createQuery(hql);
        query.setParameter("paramName", login);
        return (User) query.uniqueResult();
    }

    public void insertUser(String login, String password) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long id = (Long) session.save(new User(-1,login,password));
        transaction.commit();
        session.close();
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}