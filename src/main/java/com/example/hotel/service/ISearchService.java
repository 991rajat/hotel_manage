package com.example.hotel.service;

import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface ISearchService {
    List<HotelResponseDto> getAllHotels();
    List<HotelResponseDto> getAllHotelsWithDate(String city, Date checkIn, Date checkout) throws NotFoundException;
}
