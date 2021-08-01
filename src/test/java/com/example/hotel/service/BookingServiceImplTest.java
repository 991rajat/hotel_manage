package com.example.hotel.service;

import com.example.hotel.dao.IBookingDao;
import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;
import com.example.hotel.model.BookingStatus;
import com.example.hotel.model.Customer;
import com.example.hotel.model.Hotel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private IBookingDao iBookingDao;

    @InjectMocks
    private BookingServiceImpl iBookingService;
    List<Booking> bookingList = new ArrayList<>();
    List<BookingResponseDto> bookingResponseList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Hotel hotel1 = new Hotel(1L,"Marriott","Delhi");
        Hotel hotel2 = new Hotel(2L,"Oberoi","Agra");
        Customer customer1 = new Customer("John");
        Customer customer2 = new Customer("Robert");
        Booking booking1 = new Booking(16L, Date.valueOf("2021-02-02"),Date.valueOf("2021-02-06"), BookingStatus.ACTIVE,hotel1,customer1,2);
        Booking booking2 = new Booking(26L, Date.valueOf("2021-02-05"),Date.valueOf("2021-02-08"),BookingStatus.ACTIVE,hotel1,customer2,2);
        Booking booking3 = new Booking(11L, Date.valueOf("2021-05-23"),Date.valueOf("2021-05-29"),BookingStatus.ACTIVE,hotel2,customer2,1);
        Booking booking4 = new Booking(25L, Date.valueOf("2021-02-02"),Date.valueOf("2021-02-06"),BookingStatus.ACTIVE,hotel2,customer1,2);
        bookingList.add(booking1);
        bookingList.add(booking2);
        bookingList.add(booking3);
        bookingList.add(booking4);
        for(Booking booking: bookingList){
            BookingResponseDto bookingResponse  = new BookingResponseDto();
            bookingResponse.convertToBookingResponse(booking);
            bookingResponseList.add(bookingResponse);
        }
    }

    @Test
    @DisplayName("BookingService Test: Get all bookings")
    void getAllBookings() {

        when(iBookingService.getAllBookings()).thenReturn(bookingResponseList);
        List<BookingResponseDto> bookings = iBookingService.getAllBookings();
        assertEquals(4,bookings.size());

    }

    @Test
    @DisplayName("BookingService Test: Get booking with id don't exist.")
    void getBookingWithIdNotExist() throws NotFoundException {
        when(iBookingDao.getBookingWithId(123L)).thenThrow(new NotFoundException("Booking not found with id "+123 +" ."));
        NotFoundException e = assertThrows(NotFoundException.class,()->iBookingService.getBookingWithId(123L));
        assertEquals("Booking not found with id "+123 +" .",e.getMessage());
    }

    @Test
    @DisplayName("BookingService Test: Get booking with id don't exist.")
    void bookingHotel() throws RoomNotAvailableException, NotFoundException {
        Hotel hotel = new Hotel(2L,"Oberoi","Agra");
        Customer customer = new Customer(19L,"John");
        BookingRequestDto bookingReq = new BookingRequestDto(2L,Date.valueOf("2021-12-05"),Date.valueOf("2021-12-08"),2,19L);
        Booking booking = new Booking(45L,Date.valueOf("2021-12-05"),Date.valueOf("2021-12-08"),BookingStatus.ACTIVE,hotel,customer,2);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.convertToBookingResponse(booking);
        when(iBookingService.bookingHotel(bookingReq)).thenReturn(bookingResponseDto);
        BookingResponseDto actualBookingResponse = iBookingService.bookingHotel(bookingReq);
        assertEquals(45,actualBookingResponse.getId());
    }

    @Test
    void cancelBooking() throws NotFoundException {
//                bookingList.get(2).cancelBooking();
//        when(iBookingDao.cancelBooking(11L)).thenReturn(true);
//        iBookingDao.cancelBooking(11L);
    }
}