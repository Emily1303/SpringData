package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "competition_type")
public class CompetitionType extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public CompetitionType() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
