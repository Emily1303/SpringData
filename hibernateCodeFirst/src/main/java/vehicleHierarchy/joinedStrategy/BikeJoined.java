package vehicleHierarchy.joinedStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class BikeJoined extends VehicleJoined {

    @Id
    private long id;

    public BikeJoined() {}

    public BikeJoined(String type, String model, BigDecimal price, String fuelType) {
        super(type, model, price, fuelType);
    }

}
