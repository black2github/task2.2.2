package dao;

import model.Car;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDaoImpl implements CarDao {
    protected static Logger log = Logger.getLogger(CarDaoImpl.class.getName());

    private SessionFactory sessionFactory;

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Car> findAll() {
        log.debug("findAll: <- ");

        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("select c from Car c", Car.class);
        return query.getResultList();
    }

    @Override
    public List<Car> find(int count) {
        log.debug("find: <- " + count);

        TypedQuery<Car> query = sessionFactory.getCurrentSession()
                .createQuery("select c from Car c", Car.class);
        return query.getResultList().stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Car add(Car car) {
        log.debug("add: <- " + car);

        sessionFactory.getCurrentSession().persist(car);
        log.debug("add: -> " + car);
        return car;
    }
}
