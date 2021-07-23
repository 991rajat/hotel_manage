package com.example.hotel.repository;

import com.example.hotel.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;

@DataJpaTest
class IBookingRepositoryTest {

    @Autowired
    private IBookingRepository iBookingRepository;

    @Test
    void countBookedRoomsWithHotelWithDate() {
//        Booking booking1 = new Booking(16L, Date.valueOf("2021-02-02"),Date.valueOf("2021-02-06"),1L,3);
//        Booking booking2 = new Booking(26L, Date.valueOf("2021-02-05"),Date.valueOf("2021-02-08"),1L,2);
//        Booking booking3 = new Booking(11L, Date.valueOf("2021-05-23"),Date.valueOf("2021-05-29"),2L,5);
//        Booking booking4 = new Booking(25L, Date.valueOf("2021-02-02"),Date.valueOf("2021-02-06"),3L,2);

//        iBookingRepository.save(booking1);
//        iBookingRepository.save(booking2);
//        iBookingRepository.save(booking3);
//        iBookingRepository.save(booking4);

        int actual = iBookingRepository.countBookedRoomsWithHotelWithDate(1L,Date.valueOf("2021-02-01"),Date.valueOf("2021-02-08"));

        Assertions.assertEquals(5,actual);

    }
}