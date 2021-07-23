package com.example.hotel.dao;

import com.example.hotel.model.Booking;
import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDaoImpl implements ICustomerDao{
    @Autowired
    ICustomerRepository iCustomerRepository;
    @Autowired
    IBookingRepository iBookingRepository;
    @Override
    public List<Booking> getAllBookings(Long id) {
        List<Booking> bookings = iCustomerRepository.getAllBookings(id);
        return bookings;
    }
}
