package relations.manyToMany;

import vehicleHierarchy.tablePerClassStrategy.Truck;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private BigInteger id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToMany
    private List<Truck> trucks;

    public Driver(String fullName, List<Truck> trucks) {
        this.fullName = fullName;
        this.trucks = new ArrayList<>();
    }

    public Driver() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

}
