package ImAle.userservice.Service.Controller;

import ImAle.userservice.Entity.Person;
import ImAle.userservice.Service.PersonService;
import ImAle.userservice.Service.models.Car;
import ImAle.userservice.Service.models.Motorbike;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Repository
    @GetMapping
    public ResponseEntity<List<Person>> personsList (){
        List<Person> persons = personService.getAll();
        if(persons.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id){
        Person person = personService.getPersonById(id);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person savedPerson = personService.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    // RestTemplate
    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackGetCarsByPersonId")
    @GetMapping("/cars/{personId}")
    public ResponseEntity<List<Car>> getCarsByPersonId(@PathVariable("personId") int personId){
        Person person = personService.getPersonById(personId);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = personService.getCarsByPersonId(personId);
        return ResponseEntity.ok(cars);
    }

    @CircuitBreaker(name = "motorbikeCB", fallbackMethod = "fallbackGetMotorbikesByPersonId")
    @GetMapping("/motorbikes/{personId}")
    public ResponseEntity<List<Motorbike>> getMotorbikesByPersonId(@PathVariable("personId") int personId){
        Person person = personService.getPersonById(personId);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        List<Motorbike> motorbikes = personService.getMotorbikesByPersonId(personId);
        return ResponseEntity.ok(motorbikes);
    }

    // Feign Client
    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/car/{personId}")
    public ResponseEntity<Car> saveCar(@PathVariable("personId") int personId, @RequestBody Car car){
        Car newCar = personService.saveCar(personId, car);
        return ResponseEntity.ok(newCar);
    }

    @CircuitBreaker(name = "motorbikeCB", fallbackMethod = "fallbackSaveMotorbike")
    @PostMapping("/motorbike/{personId}")
    public ResponseEntity<Motorbike> saveMotorbike(@PathVariable("personId") int personId, @RequestBody Motorbike motorbike){
        Motorbike newMotorbike = personService.saveMotorbike(personId, motorbike);
        return ResponseEntity.ok(newMotorbike);
    }

    @GetMapping("/car/{personId}")
    public ResponseEntity<List<Car>> getCarsByPersonIdFeign(@PathVariable("personId") int personId){
        List<Car> cars = personService.getCarsByPersonIdFeign(personId);
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/motorbike/{personId}")
    public ResponseEntity<List<Motorbike>> getMotorbikesByPersonIdFeign(@PathVariable("personId") int personId){
        List<Motorbike> motorbikes = personService.getMotorbikesByPersonIdFeign(personId);
        if(motorbikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorbikes);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetVehiclesByPersonId")
    @GetMapping("/vehicles/{personId}")
    public ResponseEntity<Map<String, Object>> getVehiclesByPersonId(@PathVariable("personId") int personId){
        Map<String, Object> result = personService.getVehiclesByPersonId(personId);
        return ResponseEntity.ok(result);
    }


    // Fallbacks
    private ResponseEntity<List<Car>> fallbackGetCarsByPersonId (@PathVariable("personId") int personId, Exception e){
        return new ResponseEntity("Service is currently unavailable, please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
    }
    private ResponseEntity<Car> fallbackSaveCar (@PathVariable("personId") int personId, @RequestBody Car car, Exception e){
        return new ResponseEntity("Service is currently unavailable, please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<List<Motorbike>> fallbackGetMotorbikesByPersonId(@PathVariable("personId") int personId, Exception e){
        return new ResponseEntity("Service is currently unavailable, please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<Motorbike> fallbackSaveMotorbike(@PathVariable("personId") int personId, @RequestBody Motorbike motorbike, Exception e){
        return new ResponseEntity("Service is currently unavailable, please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<Map<String, Object>> fallbackGetVehiclesByPersonId(@PathVariable("personId") int personId, Exception e){
        return new ResponseEntity("Service is currently unavailable, please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
