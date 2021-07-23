package com.example.hotel.dao;

import com.example.hotel.model.Booking;

import java.util.List;

public interface ICustomerDao {
    List<Booking> getAllBookings(Long id);
}
