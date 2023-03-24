package ImAle.userservice.Service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private String brand;
    private String model;
    private int personId;

    public Car() {
    }
}
