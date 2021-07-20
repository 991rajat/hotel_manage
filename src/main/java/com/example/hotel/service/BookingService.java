package com.example.hotel.service;

import com.example.hotel.dto.requestDTO.BookingRequestDTO;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ISearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService{

    @Autowired
    IBookingRepository iBookingRepository;

    @Autowired
    ISearchRepository iSearchRepository;

    @Override
    public List<Booking> getAllBookings() {
        return iBookingRepository.findAll();
    }

    //TODO: Booking done to be blindly. Only SAVE No Calculations
    @Override
    public Booking bookingHotel(BookingRequestDTO booking) throws ResourceNotFoundException, RoomNotAvailableException {

        Optional<Hotel> hotel = iSearchRepository.findById(booking.getHotel_id());
        if(!hotel.isPresent()){
            throw new ResourceNotFoundException("Hotel not found with id "+booking.getHotel_id()+".");
        }
        int booked_rooms = iBookingRepository.countBookedRoomsWithHotelWithDate(booking.getHotel_id(),booking.getDate_from(),booking.getDate_to());
        int available_rooms = hotel.get().getRoomList().size() - booked_rooms;
        if(available_rooms < booking.getNo_of_rooms())
        {
            throw new RoomNotAvailableException("Room not available.");
        }
        Booking newBooking = new Booking(booking.getDate_from(),
                booking.getDate_to(),
                booking.getHotel_id(),
                booking.getNo_of_rooms()
                );
        return iBookingRepository.save(newBooking);
    }

    @Override
    public boolean cancelBooking(Long bookingId) throws ResourceNotFoundException {
        Optional<Booking> booking = iBookingRepository.findById(bookingId);
        if(!booking.isPresent()){
            throw new ResourceNotFoundException("No booking found with id "+booking+".");
        }
        iBookingRepository.deleteById(bookingId);
        return true;
    }


}
