package com.example.hotel.service;

import com.example.hotel.dao.IBookingDao;
import com.example.hotel.dao.ISearchDao;
import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;
import com.example.hotel.model.Customer;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.ICustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookingServiceImpl implements IBookingService{

    @Autowired
    IBookingDao iBookingDao;

    @Autowired
    ISearchDao iSearchDao;

    @Autowired
    ICustomerRepository iCustomerRepository;

    // @Desc : Get All Bookings.
    @Override
    public List<BookingResponseDto> getAllBookings() {
        List<BookingResponseDto> bookingResponseList = new ArrayList<>();
        List<Booking> bookingList = iBookingDao.getAllBookings();
        for(Booking booking: bookingList){
            BookingResponseDto bookingResponse  = new BookingResponseDto();
            bookingResponse.convertToBookingResponse(booking);
            bookingResponseList.add(bookingResponse);
        }
        log.info("Retrieved all the bookings.");
        return bookingResponseList;
    }

    // @Desc : Get a booking with particular id.
    @Override
    public BookingResponseDto getBookingWithId(Long bookingId) throws NotFoundException {
        Optional<Booking> booking = iBookingDao.getBookingWithId(bookingId);
        if(!booking.isPresent()) {
            log.error("Booking not found with id " + bookingId + '.');
            throw new NotFoundException("Booking not found with id " + bookingId + '.');
        }
        log.info("Retrieved the booking with booking id "+bookingId+" :"+booking.toString());
        BookingResponseDto bookingResponse = new BookingResponseDto();
        bookingResponse.convertToBookingResponse(booking.get());
        return bookingResponse;
    }

    // @Desc : Booking a hotel.

    @Override
    public BookingResponseDto bookingHotel(final BookingRequestDto bookingRequest) throws NotFoundException, RoomNotAvailableException {

        final Optional<Hotel> hotel = iSearchDao.findHotelById(bookingRequest.getHotelId());
        // Check if hotel exists
        if(!hotel.isPresent()){
            log.error("Hotel not found with id "+bookingRequest.getHotelId()+".");
            throw new NotFoundException("Hotel not found with id "+bookingRequest.getHotelId()+".");
        }
        log.info("Hotel retrieved with id "+hotel.get().getId()+" .");
        final Optional<Customer> customer = iCustomerRepository.findById(bookingRequest.getCustomerId());
        // Check if customer exists
        if(!customer.isPresent()){
            log.error("Customer not found with id "+bookingRequest.getHotelId()+".");
            throw new NotFoundException("Customer not found with id "+bookingRequest.getHotelId()+".");
        }
        log.info("Customer retrieved with id "+customer.get().getId()+" .");
        // Check if rooms are available
        int bookedRooms = iBookingDao.countOfBookedRoomsOfHotelWithDate(bookingRequest.getHotelId(),bookingRequest.getArrivingDate(),bookingRequest.getDepartureDate());
        int availableRooms = hotel.get().getRoomList().size() - bookedRooms;
        if(availableRooms < bookingRequest.getNoOfRooms()) {
            log.error("No rooms are available within "+bookingRequest.getArrivingDate()+" to "+bookingRequest.getDepartureDate()+" .");
            throw new RoomNotAvailableException("Room not available.");
        }
        // save the booking
        Booking createdBooking = new Booking(bookingRequest.getArrivingDate(), bookingRequest.getDepartureDate(), hotel.get(), customer.get(), bookingRequest.getNoOfRooms());
        createdBooking = iBookingDao.bookHotel(createdBooking);
        log.info("Booking created successfully "+ createdBooking+".");
        BookingResponseDto bookingResponse = new BookingResponseDto();
        bookingResponse.convertToBookingResponse(createdBooking);
        return bookingResponse;
    }

    // @Desc : Cancel a booking with particular id.
    @Override
    public boolean cancelBooking(Long bookingId) throws NotFoundException {
        Optional<Booking> booking = iBookingDao.getBookingWithId(bookingId);
        // Check if booking exists.
        if(!booking.isPresent()) {
            log.error("Booking not found with id " + bookingId + '.');
            throw new NotFoundException("Booking not found with id " + bookingId + '.');
        }
        log.info("Retrieved the booking with booking id "+bookingId+" :"+booking.get().toString());
        final boolean result = iBookingDao.cancelBooking(bookingId);
        log.info("Booking cancelled successfully");
        return result;
    }


}
