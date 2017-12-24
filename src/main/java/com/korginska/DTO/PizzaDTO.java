package com.korginska.DTO;

import com.korginska.domain.Pizza;

import com.korginska.exceptions.NoSuchChiefCookException;
import com.korginska.exceptions.NoSuchPizzaException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PizzaDTO extends ResourceSupport {
    Pizza pizza;
    public PizzaDTO(Pizza pizza, Link selfLink) throws NoSuchPizzaException, NoSuchChiefCookException {
        this.pizza= pizza;
        add(selfLink);
        add(linkTo(methodOn(ChiefCookController.class).getPersonsByBookID(pizza.getId())).withRel("chiefs"));
    }

    public Pizza getPizza() {
        return pizza;
    }


}