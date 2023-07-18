package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Round() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
