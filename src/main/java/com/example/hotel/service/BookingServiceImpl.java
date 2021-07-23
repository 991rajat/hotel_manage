package com.example.hotel.service;

import com.example.hotel.dao.IBookingDao;
import com.example.hotel.dao.ISearchDao;
import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;
import com.example.hotel.model.Customer;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService{

    @Autowired
    IBookingDao iBookingDao;

    @Autowired
    ISearchDao iSearchDao;

    @Autowired
    ICustomerRepository iCustomerRepository;

    @Override
    public List<Booking> getAllBookings() {
        return iBookingDao.getAllBookings();
    }

    @Override
    public Booking getBookingWithId(Long bookingId) throws ResourceNotFoundException {
        return iBookingDao.getBookingWithId(bookingId);
    }

    @Override
    public Booking bookingHotel(BookingRequestDto booking) throws ResourceNotFoundException, RoomNotAvailableException {

        Optional<Hotel> hotel = iSearchDao.findHotelById(booking.getHotelId());
        Optional<Customer> customer = iCustomerRepository.findById(booking.getCustomerId());
        if(!hotel.isPresent()){
            throw new ResourceNotFoundException("Hotel not found with id "+booking.getHotelId()+".");
        }
        if(!customer.isPresent()){
            throw new ResourceNotFoundException("Customer not found with id "+booking.getHotelId()+".");
        }
        int booked_rooms = iBookingDao.countOfBookedRoomsOfHotelWithDate(booking.getHotelId(),booking.getArrivingDate(),booking.getDepartureDate());
        int available_rooms = hotel.get().getRoomList().size() - booked_rooms;
        if(available_rooms < booking.getNoOfRooms()) {
            throw new RoomNotAvailableException("Room not available.");
        }
        Booking newBooking = new Booking(
                booking.getArrivingDate(),
                booking.getDepartureDate(),
                hotel.get(),
                customer.get(),
                booking.getNoOfRooms());
        return iBookingDao.bookHotel(newBooking);
    }

    @Override
    public boolean cancelBooking(Long bookingId) throws ResourceNotFoundException {
        iBookingDao.cancelBooking(bookingId);
        return true;
    }


}
