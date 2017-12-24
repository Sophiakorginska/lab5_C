package com.korginska.service;

import com.korginska.Repository.RestaurantRepository;
import com.korginska.Repository.ChiefCookRepository;
import com.korginska.domain.Restaurant;
import com.korginska.exceptions.ExistsChiefsForRestaurantException;
import com.korginska.exceptions.NoSuchRestaurantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sofia on 18.12.2017.
 */
    @Service
    public class RestaurantService {
        @Autowired
        RestaurantRepository restaurantRepository;
        private boolean ascending;

        @Autowired
        ChiefCookRepository chiefCookRepository;

        public List<Restaurant> getAllRestaurant() {
            return restaurantRepository.findAll();
        }

        public Restaurant getRestaurant(Long restaurant_id) throws NoSuchRestaurantException {
//        City city =cityRepository.findOne(city_id);//1.5.9
            Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();//2.0.0.M7
            if (restaurant == null) throw new NoSuchRestaurantException();
            return restaurant;
        }

        @Transactional
        public void createRestaurant(Restaurant restaurant) {
            restaurantRepository.save(restaurant);
        }

        @Transactional
        public void updateRestaurant(Restaurant uRestaurant, Long restaurant_id) throws NoSuchRestaurantException {
//        City city = cityRepository.findOne(city_id);//1.5.9
            Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();//2.0.0.M7

            if (restaurant == null) throw new NoSuchRestaurantException();
            restaurant.setRestaurant(uRestaurant.getRestaurant());
            restaurantRepository.save(restaurant);
        }

        @Transactional
        public void deleteRestaurant(Long restaurant_id) throws NoSuchRestaurantException, ExistsChiefsForRestaurantException {
//        City city = cityRepository.findOne(city_id);//1.5.9
            Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();//2.0.0.M7
            if (restaurant == null) throw new NoSuchRestaurantException();
            if (restaurant.getChiefs().size() != 0) throw new ExistsChiefsForRestaurantException();
            restaurantRepository.delete(restaurant);
        }
    }

