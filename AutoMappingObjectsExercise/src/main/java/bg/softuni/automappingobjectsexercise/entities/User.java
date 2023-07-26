package bg.softuni.automappingobjectsexercise.entities;

import bg.softuni.automappingobjectsexercise.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column
    private Boolean administrator;

    @ManyToMany
    private Set<Game> games;

    @OneToMany(targetEntity = Order.class, mappedBy = "user")
    private Set<Order> orders;

    public User(String email, String password, String fullName, Boolean administrator) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.administrator = administrator;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

}
