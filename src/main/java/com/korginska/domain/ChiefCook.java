package com.korginska.domain;

/**
 * Created by Sofia on 18.12.2017.
 */
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chiefcook")
public class ChiefCook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDChiefCook", nullable = false)
    private Long id;
    @Column(name = "Surname", nullable = false, length = 25)
    private String surname;
    @Column(name = "Name", nullable = false, length = 25)
    private String name;
    @Column(name = "Experience", nullable = true, length = 45)
    private Integer experience;
    @ManyToOne
    @JoinColumn(name = "Restaurant", referencedColumnName = "Restaurant")
    private Restaurant restaurant;
    @ManyToMany
    @JoinTable(name = "chiefpizza",
            joinColumns = @JoinColumn(name = "IDChiefCook", referencedColumnName = "IDChiefCook", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "IDPizza", referencedColumnName = "IDPizza", nullable = false))
    private Set<Pizza> pizzas;

    ChiefCook() {
    }

    ChiefCook(String surname, String name, Integer experience) {
        this.surname = surname;
        this.name = name;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChiefCook chiefCook = (ChiefCook) o;

        if (id != null ? !id.equals(chiefCook.id) : chiefCook.id != null) return false;
        if (surname != null ? !surname.equals(chiefCook.surname) : chiefCook.surname != null) return false;
        if (name != null ? !name.equals(chiefCook.name) : chiefCook.name != null) return false;
        if (experience != null ? !experience.equals(chiefCook.experience) : chiefCook.experience != null) return false;
        if (restaurant != null ? !restaurant.equals(chiefCook.restaurant) : chiefCook.restaurant != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }
}


