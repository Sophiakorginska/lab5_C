package com.korginska.Repository;
import  com.korginska.domain.ChiefCook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Sofia on 18.12.2017.
 */
@Repository
public interface ChiefCookRepository extends JpaRepository<ChiefCook, Long> {

}