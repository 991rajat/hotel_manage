package com.example.hotel.service;

import com.example.hotel.dao.ISearchDao;
import com.example.hotel.dao.SearchDaoImpl;
import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.Room;
import com.example.hotel.repository.ISearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {


    @Mock
    private SearchDaoImpl iSearchDao;
    @InjectMocks
    private SearchServiceImpl searchService;
    private List<Hotel> hotelList = new ArrayList<>();
    private List<HotelResponseDto> hotelResponseList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Hotel hotel1 = new Hotel(1L,"Marriott","Delhi");
        Room room11 = new Room(hotel1,true);
        Room room12 = new Room(hotel1,true);
        Room room13 = new Room(hotel1,true);
        Room room14 = new Room(hotel1,true);
        hotel1.setRoomList(Arrays.asList(room11,room12,room13,room14));

        Hotel hotel2 = new Hotel(2L,"Taj","Mumbai");
        Room room21 = new Room(hotel2,true);
        Room room22 = new Room(hotel2,true);
        Room room23 = new Room(hotel2,true);
        Room room24 = new Room(hotel2,true);
        hotel2.setRoomList(Arrays.asList(room21,room22,room23,room24));

        Hotel hotel3 = new Hotel(3L,"Radisson","Banglore");
        Room room31 = new Room(hotel3,true);
        Room room32 = new Room(hotel3,true);
        Room room33 = new Room(hotel3,true);
        Room room34 = new Room(hotel3,true);
        hotel3.setRoomList(Arrays.asList(room31,room32,room33,room34));
        Hotel hotel4 = new Hotel(4L,"Oberoi","Jodhpur");
        Room room41 = new Room(hotel4,true);
        Room room42 = new Room(hotel4,true);
        Room room43 = new Room(hotel4,true);
        Room room44 = new Room(hotel4,true);
        hotel4.setRoomList(Arrays.asList(room41,room42,room43,room44));
        hotelList.add(hotel1);
        hotelList.add(hotel2);
        hotelList.add(hotel3);
        hotelList.add(hotel4);
        for(Hotel hotel: hotelList){
            HotelResponseDto hotelResponse = new HotelResponseDto();
            hotelResponse.convertToHotelResponse(hotel);
            hotelResponseList.add(hotelResponse);
        }
    }

    @Test
    @DisplayName("SearchService Test: Getting all the hotels")
    void getAllHotels() {
        when(searchService.getAllHotels()).thenReturn(hotelResponseList);
        List<HotelResponseDto> actualList = searchService.getAllHotels();
        assertEquals(hotelResponseList.size(),actualList.size());
    }

    @Test
    @DisplayName("SearchService Test: Getting all the hotels within city")
    void getAllHotelsWithDate() {
//        when(searchService.getAllHotels()).thenReturn(hotelResponseList);
//        List<HotelResponseDto> actualList = searchService.getAllHotels();
//        assertEquals(hotelResponseList.size(),actualList.size());
    }
}