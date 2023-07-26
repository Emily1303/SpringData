package bg.softuni.automappingobjectsexercise.entities;

import bg.softuni.automappingobjectsexercise.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToMany(targetEntity = Game.class)
    private Set<Game> games;

}
