package com.example.hotel.service;

import com.example.hotel.model.Booking;
import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ISearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private IBookingRepository iBookingRepository;
    @Mock
    private ISearchRepository iSearchRepository;

    @InjectMocks
    private BookingServiceImpl iBookingService;
    List<Booking> bookingList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        Booking booking1 = new Booking(16L, Date.valueOf("2021-02-02"),Date.valueOf("2021-02-06"),1L,3);
//        Booking booking2 = new Booking(26L, Date.valueOf("2021-02-05"),Date.valueOf("2021-02-08"),1L,2);
//        Booking booking3 = new Booking(11L, Date.valueOf("2021-05-23"),Date.valueOf("2021-05-29"),2L,5);
//        Booking booking4 = new Booking(25L, Date.valueOf("2021-02-02"),Date.valueOf("2021-02-06"),3L,2);
//        bookingList.add(booking1);
//        bookingList.add(booking2);
//        bookingList.add(booking3);
//        bookingList.add(booking4);
    }

    @Test
    void getAllBookings() {
        iBookingService.getAllBookings();
        //when(iBookingService.getAllBookings()).thenReturn(bookingList);
        //assertEquals(4,bookingList.size());
        verify(iBookingRepository,Mockito.times(1)).findAll();
    }



    @Test
    @Disabled
    void cancelBooking() {
    }
}