package com.example.hotel.service;

import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;

import java.awt.print.Book;
import java.util.List;

public interface IBookingService {
    Booking bookingHotel(BookingRequestDto booking) throws ResourceNotFoundException, RoomNotAvailableException;

    boolean cancelBooking(Long bookingId) throws ResourceNotFoundException;

    List<Booking> getAllBookings();

    Booking getBookingWithId(Long bookingId) throws ResourceNotFoundException;
}
