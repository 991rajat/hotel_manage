package com.example.hotel.service;

import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ISearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService implements ISearchService{

    @Autowired
    private ISearchRepository iSearchRepository;

    @Autowired
    private IBookingRepository iBookingRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return iSearchRepository.findAll();
    }

    //TODO: Look for better logic
    @Override
    public List<Hotel> getAllHotelsWithDate(String city, Date checkIn, Date checkOut) throws ResourceNotFoundException {
        List<Hotel> list = new ArrayList<>();
        Optional<List<Hotel>> listWithCity = iSearchRepository.findHotelWithCity(city);
        for(Hotel h : listWithCity.get()){
            int count = iBookingRepository.countBookedRoomsWithHotelWithDate(h.getHotelId(), checkIn, checkOut);
            if((h.getRoomList().size() - count) > 0)
                list.add(h);
        }

        return list;
    }



}
