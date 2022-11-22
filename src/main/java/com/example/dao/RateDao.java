package com.example.dao;

import com.example.entity.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateDao extends JpaRepository<Rate, Integer> {
    @Query("SELECT rate FROM Rate rate Order by rate.ratedDate ASC")
    Page<Rate> sortByDateASC(Pageable pageable);

    @Query("SELECT rate FROM Rate rate Order by rate.ratedDate DESC")
    Page<Rate> sortByDateDESC(Pageable pageable);

    @Query(value = "SELECT rate FROM Rate rate WHERE rate.account.userName =:userName", nativeQuery = true)
    Page<Rate> findByUserName(@Param("userName") String userName, Pageable pageable);

    @Query("SELECT rate FROM Rate rate WHERE rate.product.id = ?1 Order by rate.ratedDate DESC")
    Page<Rate> findByProductDESC(int productId, Pageable pageable);

}
