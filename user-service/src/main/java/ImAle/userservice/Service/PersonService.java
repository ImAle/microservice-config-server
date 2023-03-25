package ImAle.userservice.Service;

import ImAle.userservice.Entity.Person;
import ImAle.userservice.Repository.PersonRepository;
import ImAle.userservice.Service.Feign.CarFeignClient;
import ImAle.userservice.Service.Feign.MotorbikeFeignClient;
import ImAle.userservice.Service.models.Car;
import ImAle.userservice.Service.models.Motorbike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private MotorbikeFeignClient motorbikeFeignClient;

    // RestTemplate
    public List<Car> getCarsByPersonId(int personId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8082/car/person/" + personId, List.class);
        return cars;
    }

    public List<Motorbike> getMotorbikesByPersonId(int personId){
        List<Motorbike> motorbikes = restTemplate.getForObject("http://localhost:8083/motorbike/person/" + personId, List.class);
        return motorbikes;
    }

    //Feign Client
    public Car saveCar(int personId, Car car){
        car.setPersonId(personId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }

    public Motorbike saveMotorbike(int personId, Motorbike motorbike){
        motorbike.setPersonId(personId);
        Motorbike newMotorbike = motorbikeFeignClient.save(motorbike);
        return newMotorbike;
    }

    public List<Car> getCarsByPersonIdFeign (int personId){
       List<Car> cars = carFeignClient.getCarsByPersonId(personId);
       return cars;
    }

    public List<Motorbike> getMotorbikesByPersonIdFeign (int personId){
        List<Motorbike> motorbikes = motorbikeFeignClient.getMotorbikesByPersonId(personId);
        return motorbikes;
    }

    public Map<String, Object> getVehiclesByPersonId(int personId){
        Map<String, Object> result = new HashMap<>();
        Person person = personRepository.findById(personId).orElse(null);
        if(person == null){
            result.put("Message", "The user does not exists");
            return result;
        }
        result.put("Person", person);

        List<Car> cars = carFeignClient.getCarsByPersonId(personId);
        if(cars.isEmpty()){
            result.put("Cars", "The person with Id " + personId + " has no cars");
        } else{
            result.put("Cars", cars);
        }

        List<Motorbike> motorbikes = motorbikeFeignClient.getMotorbikesByPersonId(personId);
        if(motorbikes.isEmpty()){
            result.put("Motorbikes", "The person with Id " + personId + " has no motorbikes");
        }else {
            result.put("Motorbikes", motorbikes);
        }

        return result;
    }

    // Repository

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Person getPersonById(Integer id){
        return personRepository.findById(id).orElse(null);
    }

    public Person save(Person givenPerson){
        Person newPerson = personRepository.save(givenPerson);
        return newPerson;
    }

}