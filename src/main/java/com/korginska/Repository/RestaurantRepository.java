package com.korginska.Repository;

import com.korginska.domain.Restaurant;

/**
 * Created by Sofia on 18.12.2017.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
