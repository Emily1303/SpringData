package vehicleHierarchy.singleTableStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class BikeSingleTable extends VehicleSingleTable {

    @Id
    private long id;

    public BikeSingleTable() {}

    public BikeSingleTable(String type, String model, BigDecimal price, String fuelType) {
        super(type, model, price, fuelType);
    }

}
