package com.example.hotel.service;

import com.example.hotel.dao.ICustomerDao;
import com.example.hotel.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    ICustomerDao iCustomerDao;
    @Override
    public List<Booking> getAllBookings(Long id) {
        return iCustomerDao.getAllBookings(id);
    }
}
