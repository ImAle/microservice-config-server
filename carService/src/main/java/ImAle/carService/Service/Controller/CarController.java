package ImAle.carService.Service.Controller;

import ImAle.carService.Entity.Car;
import ImAle.carService.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> carsList (){
        List<Car> cars = carService.getAll();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Integer id){
        Car car = carService.getCarById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car savedCar = carService.save(car);
        return ResponseEntity.ok(savedCar);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<Car>> getCarsByPerson(@PathVariable("id") int id){
        List<Car> carsList = carService.carsByPersonId(id);
        if(carsList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carsList);
    }

}
