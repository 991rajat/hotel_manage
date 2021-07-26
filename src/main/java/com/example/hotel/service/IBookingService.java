package com.example.hotel.service;

import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;

import java.util.List;

public interface IBookingService {
    BookingResponseDto bookingHotel(BookingRequestDto booking) throws NotFoundException, RoomNotAvailableException;

    boolean cancelBooking(Long bookingId) throws NotFoundException;

    List<BookingResponseDto> getAllBookings();

    BookingResponseDto getBookingWithId(Long bookingId) throws NotFoundException;
}
