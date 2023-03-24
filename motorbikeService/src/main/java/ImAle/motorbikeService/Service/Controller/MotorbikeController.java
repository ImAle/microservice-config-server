package ImAle.motorbikeService.Service.Controller;

import ImAle.motorbikeService.Entity.Motorbike;
import ImAle.motorbikeService.Service.MotorbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorbike")
public class MotorbikeController {

    @Autowired
    private MotorbikeService motorbikeService;

    @GetMapping
    public ResponseEntity<List<Motorbike>> motorbikesList (){
        List<Motorbike> motorbikes = motorbikeService.getAll();
        if(motorbikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorbikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorbike> getMotorbikeById(@PathVariable("id") Integer id){
        Motorbike motorbike = motorbikeService.getMotorbikeById(id);
        if(motorbike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorbike);
    }

    @PostMapping
    public ResponseEntity<Motorbike> saveMotorbike(@RequestBody Motorbike motorbike){
        Motorbike savedMotorbike = motorbikeService.save(motorbike);
        return ResponseEntity.ok(savedMotorbike);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<Motorbike>> getCarsByPerson(@PathVariable("id") int id){
        List<Motorbike> motorbikesList = motorbikeService.motorbikesByPersonId(id);
        if(motorbikesList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorbikesList);
    }

}
