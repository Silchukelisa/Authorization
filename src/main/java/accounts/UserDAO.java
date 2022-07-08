package accounts;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

//работает с данными
public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    //Получить пользователя по логину
    public UserProfile getUserLogin(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return ((UserProfile) criteria.add(Restrictions.eq("login", name)).uniqueResult());
    }

    //Выполнить вставку данных о пользователе
    public long insertUser(UserProfile userProfile) throws HibernateException {
        return (Long) session.save(userProfile);
    }
}
