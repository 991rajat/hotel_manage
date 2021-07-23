package com.example.hotel.service;

import com.example.hotel.dao.ISearchDao;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ISearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService{

    @Autowired
    private ISearchDao iSearchDao;

    @Autowired
    private IBookingRepository iBookingRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return iSearchDao.findAllHotels();
    }

    @Override
    public List<Hotel> getAllHotelsWithDate(String city, Date checkIn, Date checkOut) throws ResourceNotFoundException {
        List<Hotel> list = new ArrayList<>();

        List<Hotel> listWithCity = iSearchDao.findAllHotelsWithCity(city);
        for(Hotel h : listWithCity){
            int count = iBookingRepository.countBookedRoomsWithHotelWithDate(h.getId(), checkIn, checkOut);
            if((h.getRoomList().size() - count) > 0)
                list.add(h);
        }

        return list;
    }



}
