package relations.oneToMany;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    private BigInteger id;

    @Column
    private String name;

    public Company() {}

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
