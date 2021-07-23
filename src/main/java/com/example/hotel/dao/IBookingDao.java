package com.example.hotel.dao;

import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.Booking;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBookingDao {
    List<Booking> getAllBookings();
    int countOfBookedRoomsOfHotelWithDate(Long id, Date checkIn,Date checkOut);
    Booking bookHotel(Booking booking);
    void cancelBooking(Long id) throws ResourceNotFoundException;
    Optional<Booking> findBookingById(Long bookingId);

    Booking getBookingWithId(Long bookingId) throws ResourceNotFoundException;
}
