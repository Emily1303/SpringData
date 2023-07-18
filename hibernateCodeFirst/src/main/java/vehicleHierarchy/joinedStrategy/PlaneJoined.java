package vehicleHierarchy.joinedStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class PlaneJoined extends VehicleJoined {

    @Id
    private long id;

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    public PlaneJoined() {}

    public PlaneJoined(String type, String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

}
