package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("all")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("From User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("Select u From User u, Car c where c.model = :model and" +
                        " c.series = :series and u.car = c.id", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<User> resultList = query.getResultList();
        return resultList.get(0);
    }
}
