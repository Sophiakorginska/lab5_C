package com.korginska.service;

import com.korginska.Repository.ChiefCookRepository;
import com.korginska.Repository.PizzaRepository;
import com.korginska.domain.ChiefCook;
import com.korginska.domain.Pizza;
import com.korginska.exceptions.ExistsChiefCookForPizzaException;
import com.korginska.exceptions.NoSuchChiefCookException;
import com.korginska.exceptions.NoSuchPizzaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    ChiefCookRepository chiefCookRepository;

    public Set<Pizza> getPizzasByChiefId(Long person_id) throws NoSuchChiefCookException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        ChiefCook chiefCook = chiefCookRepository.findById(person_id).get();//2.0.0.M7
        if (chiefCook == null) throw new NoSuchChiefCookException();
        return chiefCook.getPizzas();
    }

    public Pizza getPizza(Long pizza_id) throws NoSuchPizzaException {
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Pizza pizza = pizzaRepository.findById(pizza_id).get();//2.0.0.M7
        if (pizza == null) throw new NoSuchPizzaException();
        return pizza;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Transactional
    public void createPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Transactional
    public void updatePizza(Pizza uPizza, Long pizza_id) throws NoSuchPizzaException {
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Pizza pizza = pizzaRepository.findById(pizza_id).get();//2.0.0.M7
        if (pizza == null) throw new NoSuchPizzaException();
        //update
        pizza.setPizzaName(uPizza.getPizzaName());
        pizza.setSize(uPizza.getSize());
        pizza.setAmount(uPizza.getAmount());
    }

    @Transactional
    public void deletePizza(Long pizza_id) throws NoSuchPizzaException, ExistsChiefCookForPizzaException {
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Pizza pizza = pizzaRepository.findById(pizza_id).get();//2.0.0.M7

        if (pizza == null) throw new NoSuchPizzaException();
        if (pizza.getChiefs().size() != 0) throw new ExistsChiefCookForPizzaException();
        pizzaRepository.delete(pizza);
    }
}