package service;

import dao.CarDao;
import model.Car;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.config.AppConfig;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    protected static Logger log = Logger.getLogger(CarServiceImpl.class.getName());

    private CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public Car add(Car car) {
        log.debug("add: <- " + car);
        return carDao.add(car);
    }

    @Transactional
    @Override
    public List<Car> find(int count) {
        log.debug("find: <- " + count);

        // При запросе /cars?count=2 должен отобразиться список из 2 машин,
        // при /cars?count=3 - из 3, и тд. При count ≥ 5 выводить весь список машин.
        count = (count >=1 && count < 5) ? count : -1;
        if (count >= 1) {
            return carDao.find(count);
        } else {
            return carDao.findAll();
        }
    }

    @Transactional
    @Override
    public List<Car> findAll() {
        log.debug("findAll: <-");
        return carDao.findAll();
    }
}
