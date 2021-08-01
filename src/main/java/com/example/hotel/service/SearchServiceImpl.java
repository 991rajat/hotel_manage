package com.example.hotel.service;

import com.example.hotel.dao.ISearchDao;
import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.IBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class SearchServiceImpl implements ISearchService{

    @Autowired
    private ISearchDao iSearchDao;

    @Autowired
    private IBookingRepository iBookingRepository;

    //@Desc : Get all the hotels.
    @Override
    public List<HotelResponseDto> getAllHotels() {
        final List<Hotel> hotelList = iSearchDao.findAllHotels();
        final List<HotelResponseDto> responseHotelList = new ArrayList<>();
        for(Hotel hotel: hotelList) {
            HotelResponseDto responseHotel = new HotelResponseDto();
            responseHotel.convertToHotelResponse(hotel);
            responseHotelList.add(responseHotel);
        }
        log.info("Retrieved all the hotels.");
        return responseHotelList;
    }
    //@Desc : Get all the hotels with availability of rooms within the city
    @Override
    public List<HotelResponseDto> getAllHotelsWithDate(final String city, final Date arrivingDate, final Date departureDate) throws NotFoundException {
        final List<Hotel> hotelListWithRooms = new ArrayList<>();

        // Getting list of hotels with particular city
        final List<Hotel> hotelListWithCity = iSearchDao.findAllHotelsWithCity(city);
        log.info("Found " + hotelListWithCity.size()+" hotels within city "+city);

        // Getting list of hotels within particular city which have rooms available
        for(Hotel h : hotelListWithCity){
            int count = iBookingRepository.countBookedRoomsWithHotelWithDate(h.getId(), arrivingDate, departureDate);
            if((h.getRoomList().size() - count) > 0)
                hotelListWithRooms.add(h);
        }
        log.info("Found " + hotelListWithRooms.size()+" hotels with available rooms within "+city);
        final List<HotelResponseDto> hotelResponseList = new ArrayList<>();
        for(Hotel hotel: hotelListWithRooms) {
            HotelResponseDto responseHotel = new HotelResponseDto();
            responseHotel.convertToHotelResponse(hotel);
            hotelResponseList.add(responseHotel);
        }
        return hotelResponseList;
    }



}
