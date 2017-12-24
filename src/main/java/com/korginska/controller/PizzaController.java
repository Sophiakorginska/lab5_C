package com.korginska.controller;

import com.korginska.DTO.PizzaDTO;
import com.korginska.domain.Pizza;
import com.korginska.exceptions.ExistsChiefCookForPizzaException;
import com.korginska.exceptions.NoSuchChiefCookException;
import com.korginska.exceptions.NoSuchPizzaException;
import com.korginska.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PizzaController {
    @Autowired
    PizzaService pizzaService;

    @GetMapping(value = "/api/pizza/chiefcook/{IDChiefCook}")
    public ResponseEntity<List<PizzaDTO>> getBooksByPersonID(@PathVariable Long person_id) throws NoSuchChiefCookException, NoSuchPizzaException {
        Set<Pizza> pizzaList = pizzaService.getPizzasByChiefId(person_id);
        Link link = linkTo(methodOn(PizzaController.class).getAllPizzas()).withSelfRel();

        List<PizzaDTO> pizzaDTOS = new ArrayList<>();
        for (Pizza entity : pizzaList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            PizzaDTO dto = new PizzaDTO(entity, selfLink);
            pizzaDTOS.add(dto);
        }

        return new ResponseEntity<>(pizzaDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/pizza/{IDPizza}")
    public ResponseEntity<PizzaDTO> getPizza(@PathVariable Long pizza_id) throws NoSuchPizzaException, NoSuchChiefCookException {
        Pizza pizza = pizzaService.getPizza(pizza_id);
        Link link = linkTo(methodOn(PizzaController.class).getPizza(pizza_id)).withSelfRel();

        PizzaDTO pizzaDTO = new PizzaDTO(pizza, link);

        return new ResponseEntity<>(pizzaDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/pizza")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() throws NoSuchPizzaException, NoSuchChiefCookException {
        List<Pizza> pizzaList = pizzaService.getAllPizzas();
        Link link = linkTo(methodOn(PizzaController.class).getAllPizzas()).withSelfRel();

        List<PizzaDTO> pizzaDTO = new ArrayList<>();
        for (Pizza entity : pizzaList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            PizzaDTO dto = new PizzaDTO(entity, selfLink);
            pizzaDTO.add(dto);
        }

        return new ResponseEntity<>(pizzaDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/pizza")
    public ResponseEntity<PizzaDTO> addBook(@RequestBody Pizza newPizza) throws NoSuchPizzaException, NoSuchChiefCookException {
        pizzaService.createPizza(newPizza);
        Link link = linkTo(methodOn(PizzaController.class).getPizza(newPizza.getId())).withSelfRel();

        PizzaDTO pizzaDTO = new PizzaDTO(newPizza, link);

        return new ResponseEntity<>(pizzaDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/pizza/{IDPizza}")
    public ResponseEntity<PizzaDTO> updateBook(@RequestBody Pizza uPizza, @PathVariable Long pizza_id) throws NoSuchPizzaException, NoSuchChiefCookException{
        pizzaService.updatePizza(uPizza, pizza_id);
        Pizza pizza = pizzaService.getPizza(pizza_id);
        Link link = linkTo(methodOn(PizzaController.class).getPizza(pizza_id)).withSelfRel();

        PizzaDTO pizzaDTO = new PizzaDTO(pizza, link);

        return new ResponseEntity<>(pizzaDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/pizza/{IDPizza}")
    public  ResponseEntity deletePizza(@PathVariable Long pizza_id) throws ExistsChiefCookForPizzaException, NoSuchPizzaException {
        pizzaService.deletePizza(pizza_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}




