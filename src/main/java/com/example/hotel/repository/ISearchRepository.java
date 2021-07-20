package com.example.hotel.repository;

import com.example.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISearchRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "select * from hotel where hotel_city=:city",nativeQuery = true)
    Optional<List<Hotel>> findHotelWithCity(@Param("city")String city);
}
