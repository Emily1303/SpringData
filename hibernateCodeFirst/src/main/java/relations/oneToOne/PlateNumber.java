package relations.oneToOne;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "plate_number")
public class PlateNumber {

    @Id
    private BigInteger id;

    @Basic
    private String number;

    public PlateNumber() {}

    public PlateNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
