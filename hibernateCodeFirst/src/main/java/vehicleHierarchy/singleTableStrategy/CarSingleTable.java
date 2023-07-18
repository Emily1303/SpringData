package vehicleHierarchy.singleTableStrategy;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class CarSingleTable extends VehicleSingleTable {

    @Id
    private long id;

    @Basic
    private int seats;

    public CarSingleTable() {}

    public CarSingleTable(String type, String model, BigDecimal price, String fuelType, int seats) {
        super(type, model, price, fuelType);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}
