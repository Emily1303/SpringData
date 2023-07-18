package vehicleHierarchy.tablePerClassStrategy;

import relations.oneToOne.PlateNumber;
import vehicleHierarchy.tablePerClassStrategy.Vehicle;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    @Id
    private long id;

    @Basic
    private int seats;

    @OneToOne
    private PlateNumber plateNumber;

    public Car() {}

    public Car(String type, String model, BigDecimal price, String fuelType, int seats, PlateNumber plateNumber) {
        super(type, model, price, fuelType);
        this.seats = seats;
        this.plateNumber = plateNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }

}
