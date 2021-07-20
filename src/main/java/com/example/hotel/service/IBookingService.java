package com.example.hotel.service;

import com.example.hotel.dto.requestDTO.BookingRequestDTO;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;

import java.util.List;

public interface IBookingService {
    Booking bookingHotel(BookingRequestDTO booking) throws ResourceNotFoundException, RoomNotAvailableException;

    boolean cancelBooking(Long bookingId) throws ResourceNotFoundException;

    List<Booking> getAllBookings();
}
