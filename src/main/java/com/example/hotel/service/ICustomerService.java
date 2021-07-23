package com.example.hotel.service;

import com.example.hotel.model.Booking;

import java.util.List;

public interface ICustomerService {
    List<Booking> getAllBookings(Long id);
}
