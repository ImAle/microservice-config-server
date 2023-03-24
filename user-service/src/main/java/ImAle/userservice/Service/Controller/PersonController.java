package ImAle.userservice.Service.Controller;

import ImAle.userservice.Entity.Person;
import ImAle.userservice.Service.PersonService;
import ImAle.userservice.Service.models.Car;
import ImAle.userservice.Service.models.Motorbike;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/cars/{personId}")
    public ResponseEntity<List<Car>> getCarsByPersonId(@PathVariable("personId") int personId){
        Person person = personService.getPersonById(personId);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = personService.getCarsByPersonId(personId);
        return ResponseEntity.ok(cars);
    }

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
    @PostMapping("/car/{personId}")
    public ResponseEntity<Car> saveCar(@PathVariable("personId") int personId, @RequestBody Car car){
        Car newCar = personService.saveCar(personId, car);
        return ResponseEntity.ok(newCar);
    }

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

    @GetMapping("/vehicles/{personId}")
    public ResponseEntity<Map<String, Object>> getVehiclesByPersonId(@PathVariable("personId") int personId){
        Map<String, Object> result = personService.getVehiclesByPersonId(personId);
        return ResponseEntity.ok(result);
    }
}
