package vehicleHierarchy.singleTableStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trucks")
public class TruckSingleTable extends VehicleSingleTable {

    @Id
    private long id;

    @Column(name = "load_capacity")
    private double loadCapacity;

    public TruckSingleTable() {}

    public TruckSingleTable(String type, String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

}
