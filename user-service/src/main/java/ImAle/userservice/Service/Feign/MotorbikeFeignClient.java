package ImAle.userservice.Service.Feign;

import ImAle.userservice.Service.models.Motorbike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "motorbike-service", path = "motorbike")
public interface MotorbikeFeignClient {

    @PostMapping
    public Motorbike save(@RequestBody Motorbike motorbike);

    @GetMapping("/person/{personId}")
    public List<Motorbike> getMotorbikesByPersonId(@PathVariable("personId") int personId);
}
