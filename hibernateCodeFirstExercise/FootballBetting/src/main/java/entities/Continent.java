package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Continent() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
