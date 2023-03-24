package ImAle.motorbikeService.Service;

import ImAle.motorbikeService.Entity.Motorbike;
import ImAle.motorbikeService.Repository.MotorbikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorbikeService {

    @Autowired
    private MotorbikeRepository motorbikeRepository;

    public List<Motorbike> getAll(){
        return motorbikeRepository.findAll();
    }

    public Motorbike getMotorbikeById(Integer id){
        return motorbikeRepository.findById(id).orElse(null);
    }

    public Motorbike save(Motorbike givenMotorbike){
        Motorbike newMotorbike = motorbikeRepository.save(givenMotorbike);
        return newMotorbike;
    }

    public List<Motorbike> motorbikesByPersonId (int personId){
        return motorbikeRepository.findByPersonId(personId);
    }
}
