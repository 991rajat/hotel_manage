package com.example.hotel.repository;

import com.example.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Long> {

    @Query(value = "select COALESCE(sum(booked_rooms), 0) from booking where hotel_id = :id and :checkOut >= date_from and :checkIn <= date_to", nativeQuery = true)
    int countBookedRoomsWithHotelWithDate(@Param("id")Long id,@Param("checkIn") Date checkIn,@Param("checkOut") Date checkOut);
}
