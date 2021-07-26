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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
        return bookingResponseList;
    }

    // @Desc : Get a booking with particular id.
    @Override
    public BookingResponseDto getBookingWithId(Long bookingId) throws NotFoundException {
        Optional<Booking> booking = iBookingDao.getBookingWithId(bookingId);
        if(!booking.isPresent())
            throw new NotFoundException("Booking not found with id " +bookingId+'.');
        BookingResponseDto bookingResponse = new BookingResponseDto();
        bookingResponse.convertToBookingResponse(booking.get());
        return bookingResponse;
    }

    // @Desc : Booking a hotel.
    @Override
    public BookingResponseDto bookingHotel(BookingRequestDto bookingRequest) throws NotFoundException, RoomNotAvailableException {

        Optional<Hotel> hotel = iSearchDao.findHotelById(bookingRequest.getHotelId());
        if(!hotel.isPresent()){
            throw new NotFoundException("Hotel not found with id "+bookingRequest.getHotelId()+".");
        }

        Optional<Customer> customer = iCustomerRepository.findById(bookingRequest.getCustomerId());
        if(!customer.isPresent()){
            throw new NotFoundException("Customer not found with id "+bookingRequest.getHotelId()+".");
        }

        int bookedRooms = iBookingDao.countOfBookedRoomsOfHotelWithDate(bookingRequest.getHotelId(),bookingRequest.getArrivingDate(),bookingRequest.getDepartureDate());
        int availableRooms = hotel.get().getRoomList().size() - bookedRooms;
        if(availableRooms < bookingRequest.getNoOfRooms()) {
            throw new RoomNotAvailableException("Room not available.");
        }
        Booking newBooking = new Booking(bookingRequest.getArrivingDate(), bookingRequest.getDepartureDate(), hotel.get(), customer.get(), bookingRequest.getNoOfRooms());
        newBooking = iBookingDao.bookHotel(newBooking);
        BookingResponseDto bookingResponse = new BookingResponseDto();
        bookingResponse.convertToBookingResponse(newBooking);
        return bookingResponse;
    }

    // @Desc : Cancel a booking with particular id.
    @Override
    public boolean cancelBooking(Long bookingId) throws NotFoundException {
        Optional<Booking> booking = iBookingDao.getBookingWithId(bookingId);
        if(!booking.isPresent())
            throw new NotFoundException("Booking not found with id " +bookingId+'.');
        iBookingDao.cancelBooking(bookingId);
        return true;
    }


}
