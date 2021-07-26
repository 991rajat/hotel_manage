package com.example.hotel.repository;

import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Long> {

    @Query(value = "select COALESCE(sum(booked_rooms), 0) from booking where hotel_id = :id and status = 0 and :departureDate >= arriving_date and :arrivingDate <= departure_date", nativeQuery = true)
    int countBookedRoomsWithHotelWithDate(@Param("id")Long id,@Param("arrivingDate") Date arrivingDate,@Param("departureDate") Date departureDate);


}
