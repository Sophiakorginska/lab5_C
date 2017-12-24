package com.korginska.controller;


import com.korginska.DTO.RestaurantDTO;
import com.korginska.domain.Restaurant;
import com.korginska.exceptions.NoSuchChiefCookException;
import com.korginska.exceptions.NoSuchPizzaException;
import com.korginska.exceptions.NoSuchRestaurantException;
import com.korginska.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping(value = "/api/restaurant")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() throws NoSuchChiefCookException, NoSuchPizzaException, NoSuchRestaurantException{
        List<Restaurant> restaurantList = restaurantService.getAllRestaurant();
        Link link = linkTo(methodOn(RestaurantController.class).getAllRestaurants()).withSelfRel();

        List<RestaurantDTO> restaurantDTO = new ArrayList<>();
        for (Restaurant entity : restaurantList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getID()).withSelfRel();
            RestaurantDTO dto = new RestaurantDTO(entity, selfLink);
            restaurantDTO.add(dto);
        }

        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/city/{city_id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Long city_id) throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        City city = cityService.getCity(city_id);
        Link link = linkTo(methodOn(CityController.class).getCity(city_id)).withSelfRel();

        CityDTO cityDTO = new CityDTO(city, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/city/{city_id}")
    public  ResponseEntity<CityDTO> addCity(@RequestBody City newCity) throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        cityService.createCity(newCity);
        Link link = linkTo(methodOn(CityController.class).getCity(newCity.getId())).withSelfRel();

        CityDTO cityDTO = new CityDTO(newCity, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/city/{city_id}")
    public  ResponseEntity<CityDTO> updateCity(@RequestBody City ucity, @PathVariable Long city_id) throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        cityService.updateCity(ucity, city_id);
        City city = cityService.getCity(city_id);
        Link link = linkTo(methodOn(CityController.class).getCity(city_id)).withSelfRel();

        CityDTO cityDTO = new CityDTO(city, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/city/{city_id}")
    public  ResponseEntity deleteCity(@PathVariable Long city_id) throws NoSuchCityException, ExistsPersonsForCityException {
        cityService.deleteCity(city_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}