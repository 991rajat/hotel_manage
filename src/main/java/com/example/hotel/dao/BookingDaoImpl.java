package com.example.hotel.dao;

import com.example.hotel.exception.NotFoundException;
import com.example.hotel.model.Booking;
import com.example.hotel.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookingDaoImpl implements IBookingDao{
    @Autowired
    private IBookingRepository iBookingRepository;

    //@Desc : Get a list of all bookings.
    @Override
    public List<Booking> getAllBookings() {
        return iBookingRepository.findAll();
    }

    //@Desc : Get count of booked rooms of hotel with particular arriving date and departure date.
    @Override
    public int countOfBookedRoomsOfHotelWithDate(Long id, Date arrivingDate, Date departureDate) {
        return iBookingRepository.countBookedRoomsWithHotelWithDate(id,arrivingDate,departureDate);
    }

    //@Desc : Booking a hotel.
    @Override
    @Transactional
    public Booking bookHotel(Booking booking) {
        return iBookingRepository.save(booking);
    }


    //@Desc : Cancel a booking with given id.
    @Override
    @Transactional
    public boolean cancelBooking(Long id) throws NoSuchElementException {
        Optional<Booking> booking = iBookingRepository.findById(id);
        booking.get().cancelBooking();
        iBookingRepository.save(booking.get());
        return true;
    }


    //@Desc : Get a booking with a given id.
    @Override
    public Optional<Booking> getBookingWithId(Long bookingId) throws NoSuchElementException {
        return iBookingRepository.findById(bookingId);
    }
}
