package ImAle.userservice.Service.Feign;

import ImAle.userservice.Service.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8082", path = "/car")
public interface CarFeignClient {

    @PostMapping
    public Car save(@RequestBody Car car);

    @GetMapping("/person/{personId}")
    public List<Car> getCarsByPersonId(@PathVariable("personId") int personId);
}
