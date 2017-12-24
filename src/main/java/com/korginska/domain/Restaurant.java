package com.korginska.domain;

/**
 * Created by Sofia on 18.12.2017.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Column(name = "restaurant", nullable = false, length = 25)
    private String restaurant;
    @OneToMany(mappedBy = "restaurant")
    private List<ChiefCook> chiefs;

    Restaurant(){}
    Restaurant(String restaurant){
        this.restaurant=restaurant;
    }

    public String getRestaurant() {

        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public List<ChiefCook> getChiefs() {
        return chiefs;
    }

    public void setChiefs(List<ChiefCook> chiefs) {
        this.chiefs = chiefs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (restaurant != null ? !restaurant.equals(that.restaurant) : that.restaurant != null) return false;
        return chiefs != null ? chiefs.equals(that.chiefs) : that.chiefs == null;
    }

    @Override
    public int hashCode() {
        int result = restaurant != null ? restaurant.hashCode() : 0;
        result = 31 * result + (chiefs != null ? chiefs.hashCode() : 0);
        return result;
    }}

