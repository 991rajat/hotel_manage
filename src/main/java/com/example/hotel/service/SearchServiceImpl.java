package com.example.hotel.service;

import com.example.hotel.dao.ISearchDao;
import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.IBookingRepository;
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

    //@Desc : Get all the hotels.
    @Override
    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotelList = iSearchDao.findAllHotels();
        List<HotelResponseDto> responseHotelList = new ArrayList<>();
        for(Hotel hotel: hotelList) {
            HotelResponseDto responseHotel = new HotelResponseDto();
            responseHotel.convertToHotelResponse(hotel);
            responseHotelList.add(responseHotel);
        }
        return responseHotelList;
    }
    //TODO : FINAL keywords
    @Override
    public List<HotelResponseDto> getAllHotelsWithDate(String city, Date arrivingDate, Date departureDate) throws NotFoundException {
        List<Hotel> hotelList = new ArrayList<>();

        List<Hotel> listWithCity = iSearchDao.findAllHotelsWithCity(city);
        for(Hotel h : listWithCity){
            int count = iBookingRepository.countBookedRoomsWithHotelWithDate(h.getId(), arrivingDate, departureDate);
            if((h.getRoomList().size() - count) > 0)
                hotelList.add(h);
        }
        List<HotelResponseDto> hotelResponseList = new ArrayList<>();
        for(Hotel hotel: hotelList) {
            HotelResponseDto responseHotel = new HotelResponseDto();
            responseHotel.convertToHotelResponse(hotel);
            hotelResponseList.add(responseHotel);
        }

        return hotelResponseList;
    }



}
