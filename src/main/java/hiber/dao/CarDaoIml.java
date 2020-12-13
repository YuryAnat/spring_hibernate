package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoIml implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);

    }

    @Override
    @SuppressWarnings("all")
    public Car getCar(long id) {
        return sessionFactory.getCurrentSession().createQuery("From Car car where car.id = :id", Car.class).getSingleResult();
    }
}
