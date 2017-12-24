package com.korginska.DTO;

import com.korginska.domain.Restaurant;
import com.korginska.exceptions.NoSuchChiefCookException;
import com.korginska.exceptions.NoSuchRestaurantException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Sofia on 19.12.2017.
 */
public class RestaurantDTO extends ResourceSupport {
    Restaurant restaurant;
    public RestaurantDTO(Restaurant restaurant, Link selfLink) throws NoSuchChiefCookException, NoSuchRestaurantException, NoSuchRestaurantException {
        this.restaurant=restaurant;
        add(selfLink);
        add(linkTo(methodOn(ChiefCookController.class).getPersonsByRestaurantID(restaurant.getId())).withRel("chiefs"));
    }

    public Long getRestaurantId() { return restaurant.getId(); }

    public String getCity() {
        return city.getCity();
    }
}
