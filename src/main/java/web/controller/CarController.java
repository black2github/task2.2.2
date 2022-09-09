package web.controller;

import model.Car;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CarService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {
    protected static Logger log = Logger.getLogger(CarController.class.getName());

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostConstruct
    public void init() {
        // Создайте список из 5 машин.
        createCarList(5);
    }

    @GetMapping("")
    public String handle(@RequestParam(value = "count", required = false, defaultValue = "-1") Integer count,
                         ModelMap model) {
        log.debug("handle: <- count=" + count + ", model=" + model);

        List<Car> cars = carService.find(count);

        log.debug("handle:" + cars);
        model.addAttribute("cars", cars);

        log.debug("handle: ->");
        return "cars";
    }

    private void createCarList(int count) {
        log.debug("createCarList: <- count=" + count);

        List<Car> cars = carService.find(count);
        if (cars.isEmpty()) {
            for (int i = 0; i < count; i++) {
                Car car = carService.add(new Car("model" + i, i, "plate" + i));
            }
        }
    }
}
