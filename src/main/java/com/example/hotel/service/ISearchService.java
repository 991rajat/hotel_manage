package com.example.hotel.service;

import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.Hotel;

import java.util.Date;
import java.util.List;

public interface ISearchService {
    List<Hotel> getAllHotels();
    List<Hotel> getAllHotelsWithDate(String city, Date checkIn, Date checkout) throws ResourceNotFoundException;
}
