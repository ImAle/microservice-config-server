package ImAle.carService.Service;

import ImAle.carService.Entity.Car;
import ImAle.carService.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(Integer id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car givenCar){
        Car newCar = carRepository.save(givenCar);
        return newCar;
    }

    public List<Car> carsByPersonId (int personId){
        return carRepository.findByPersonId(personId);
    }
}
