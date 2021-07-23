package com.example.hotel.dao;

import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.Booking;
import com.example.hotel.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingDaoImpl implements IBookingDao{
    @Autowired
    private IBookingRepository iBookingRepository;


    @Override
    public List<Booking> getAllBookings() {
        return iBookingRepository.findAll();
    }

    @Override
    public int countOfBookedRoomsOfHotelWithDate(Long id, Date checkIn, Date checkOut) {
        return iBookingRepository.countBookedRoomsWithHotelWithDate(id,checkIn,checkOut);
    }

    @Override
    public Booking bookHotel(Booking booking) {
        return iBookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long id) throws ResourceNotFoundException {
        Optional<Booking> booking = findBookingById(id);
        if(!booking.isPresent())
            throw new ResourceNotFoundException("Booking not found.");
        booking.get().cancelBooking();
        iBookingRepository.save(booking.get());

    }

    @Override
    public Optional<Booking> findBookingById(Long bookingId) {
        return iBookingRepository.findById(bookingId);
    }

    @Override
    public Booking getBookingWithId(Long bookingId) throws ResourceNotFoundException {
        Optional<Booking> booking = findBookingById(bookingId);
        if(!booking.isPresent())
            throw new ResourceNotFoundException("Booking not found with "+bookingId+".");
        return booking.get();
    }
}
