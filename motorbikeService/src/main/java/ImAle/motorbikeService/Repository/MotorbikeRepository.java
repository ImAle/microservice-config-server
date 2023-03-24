package ImAle.motorbikeService.Repository;

import ImAle.motorbikeService.Entity.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Integer> {

     List<Motorbike> findByPersonId(int personId);

}
