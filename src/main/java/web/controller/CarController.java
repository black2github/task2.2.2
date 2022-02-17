package web.controller;

import model.Car;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarController {
    protected static Logger log = Logger.getLogger(CarController.class.getName());

    @Autowired
    CarService carService;

    @GetMapping(value = "/cars")
    public String handle(@RequestParam(value = "count", required = false, defaultValue = "-1") Integer count,
                         ModelMap model) {
        log.debug("handle: <- count=" + count + ", model=" + model);

        // При запросе /cars?count=2 должен отобразиться список из 2 машин,
        // при /cars?count=3 - из 3, и тд. При count ≥ 5 выводить весь список машин.
        count = (count >=1 && count < 5) ? count : -1;

        List<Car> cars = carService.find(count);

        // Создайте список из 5 машин.
        if (cars.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                Car car = carService.add(new Car("model" + i, i, "plate" + i));
                log.debug("handle:" + car);
            }
            cars = carService.find(count);
        }

        log.debug("handle:" + cars);
        model.addAttribute("cars", cars);
        log.debug("handle: ->");
        return "cars";
    }

}
