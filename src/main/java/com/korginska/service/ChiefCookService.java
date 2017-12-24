package com.korginska.service;

import com.korginska.Repository.ChiefCookRepository;
import com.korginska.Repository.PizzaRepository;
import com.korginska.Repository.RestaurantRepository;
import com.korginska.domain.ChiefCook;
import com.korginska.domain.Pizza;
import com.korginska.domain.Restaurant;
import com.korginska.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by Sofia on 18.12.2017.
 */
public class ChiefCookService {
    @Autowired
    ChiefCookRepository chiefCookRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    public List<ChiefCook> getChiefByRestaurantId(Long restaurant_id) throws NoSuchRestaurantException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();//2.0.0.M7
        if (restaurant == null) throw new NoSuchRestaurantException();
        return restaurant.getChiefs();
    }

    public ChiefCook getChiefCook(Long person_id) throws NoSuchChiefCookException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        ChiefCook chiefCook = chiefCookRepository.findById(person_id).get();//2.0.0.M7
        if (chiefCook == null) throw new NoSuchChiefCookException();
        return chiefCook;
    }

    public List<ChiefCook> getAllChiefs() {
        return chiefCookRepository.findAll();
    }

    public Set<ChiefCook> getChiefsByPizzaId(Long pizza_id) throws NoSuchPizzaException {
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Pizza pizza = pizzaRepository.findById(pizza_id).get();//2.0.0.M7
        if (pizza == null) throw new NoSuchPizzaException();
        return pizza.getChiefs();
    }

    @Transactional
    public void createChiefCook(ChiefCook chiefCook, Long restaurant_id) throws NoSuchRestaurantException {
        if (restaurant_id > 0) {
//            City city = cityRepository.findOne(city_id);//1.5.9
            Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();//2.0.0.M7
            if (restaurant == null) throw new NoSuchRestaurantException();
            chiefCook.setRestaurant(restaurant);
        }
        chiefCookRepository.save(chiefCook);
    }

    @Transactional
    public void updateChiefCook(ChiefCook uPerson, Long person_id, Long restaurant_id) throws NoSuchRestaurantException, NoSuchChiefCookException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();//2.0.0.M7
        if (restaurant_id > 0) {
            if (restaurant == null) throw new NoSuchRestaurantException();
        }
//        Person person = personRepository.findOne(person_id);//1.5.9
        ChiefCook chiefCook = chiefCookRepository.findById(person_id).get();//2.0.0.M7
        if (chiefCook == null) throw new NoSuchChiefCookException();
        //update
        chiefCook.setSurname(uPerson.getSurname());
        chiefCook.setName(uPerson.getName());
        chiefCook.setExperience(uPerson.getExperience());
        if (restaurant_id > 0) chiefCook.setRestaurant(restaurant);
        else chiefCook.setRestaurant(null);
       /* person.setStreet(uPerson.getStreet());
        person.setApartment(uPerson.getApartment());*/
        chiefCookRepository.save(chiefCook);
    }

    @Transactional
    public void deleteChiefCook(Long person_id) throws NoSuchChiefCookException, ExistsPizzasForChiefCookException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        ChiefCook chiefCook = chiefCookRepository.findById(person_id).get();//2.0.0.M7
        if (chiefCook == null) throw new NoSuchChiefCookException();
        if (chiefCook.getPizzas().size() != 0) throw new ExistsPizzasForChiefCookException();
        chiefCookRepository.delete(chiefCook);
    }

    @Transactional
    public void addPizzaForChiefCook(Long person_id, Long pizza_id)
            throws NoSuchChiefCookException, NoSuchPizzaException, AlreadyExistsPizzaInChiefCookException, PizzaAbsentException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        ChiefCook chiefCook = chiefCookRepository.findById(person_id).get();//2.0.0.M7
        if (chiefCook == null) throw new NoSuchChiefCookException();
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Pizza pizza = pizzaRepository.findById(pizza_id).get();//2.0.0.M7
        if (pizza == null) throw new NoSuchPizzaException();
        if (chiefCook.getPizzas().contains(pizza) == true) throw new AlreadyExistsPizzaInChiefCookException();
        if (pizza.getAmount() <= pizza.getChiefs().size()) throw new PizzaAbsentException();
        chiefCook.getPizzas().add(pizza);
        chiefCookRepository.save(chiefCook);
    }

    @Transactional
    public void removePizzaForChiekCook(Long person_id, Long pizza_id)
            throws NoSuchChiefCookException, NoSuchPizzaException, ChiefCookHasNotPizzaException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        ChiefCook chiefCook = chiefCookRepository.findById(person_id).get();//2.0.0.M7
        if (chiefCook == null) throw new NoSuchChiefCookException();
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Pizza pizza = pizzaRepository.findById(pizza_id).get();//2.0.0.M7
        if (pizza == null) throw new NoSuchPizzaException();
        if (chiefCook.getPizzas().contains(pizza) == false) throw new ChiefCookHasNotPizzaException();
        chiefCook.getPizzas().remove(pizza);
        chiefCookRepository.save(chiefCook);
    }
}

