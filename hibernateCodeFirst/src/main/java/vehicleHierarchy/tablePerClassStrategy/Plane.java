package vehicleHierarchy.tablePerClassStrategy;

import relations.oneToMany.Company;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle {

    @Id
    private long id;

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @ManyToOne
    private Company company;

    public Plane() {}

    public Plane(String type, String model,
                 BigDecimal price, String fuelType, int passengerCapacity, Company company) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
        this.company = company;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
