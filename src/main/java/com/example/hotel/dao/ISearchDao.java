package com.example.hotel.dao;

import com.example.hotel.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface ISearchDao {
    List<Hotel> findAllHotels();
    List<Hotel> findAllHotelsWithCity(String city);
    Optional<Hotel> findHotelById(Long id);

}
