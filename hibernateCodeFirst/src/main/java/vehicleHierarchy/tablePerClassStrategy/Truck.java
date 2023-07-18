package vehicleHierarchy.tablePerClassStrategy;

import relations.manyToMany.Driver;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {

    @Id
    private long id;

    @Column(name = "load_capacity")
    private double loadCapacity;

    public Truck() {}

    public Truck(String type, String model,
                 BigDecimal price, String fuelType, double loadCapacity, List<Driver> drivers) {
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
