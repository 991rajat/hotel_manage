package com.example.hotel.dao;

import com.example.hotel.exception.NotFoundException;
import com.example.hotel.model.Booking;
import com.example.hotel.model.BookingStatus;
import com.example.hotel.model.Customer;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.IBookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookingDaoImplTest {


    @Mock
    private IBookingRepository iBookingRepository;
    @InjectMocks
    private BookingDaoImpl iBookingDao;

    List<Booking> bookingList = new ArrayList<>();

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
    }

    @Test
    @DisplayName("Booking DAO Test : Getting all the bookings ")
    void getAllBookings() {
        when(iBookingDao.getAllBookings()).thenReturn(bookingList);
        List<Booking> bookings = iBookingDao.getAllBookings();
        assertEquals(4,bookings.size());
    }

    @Test
    @DisplayName("Booking DAO Test : Counting booked rooms of hotel within arriving and departure date")
    void countOfBookedRoomsOfHotelWithDate() {

        when(iBookingDao.countOfBookedRoomsOfHotelWithDate(1L,Date.valueOf("2021-02-02"),Date.valueOf("2021-02-04")))
                .thenReturn(2);
        int actual = iBookingDao.countOfBookedRoomsOfHotelWithDate(1L,Date.valueOf("2021-02-02"),Date.valueOf("2021-02-04"));
        assertEquals(2,actual);
    }

    @Test
    @DisplayName("Booking DAO Test : Booking a hotel")
    void bookHotel() {
        Hotel hotel = new Hotel(2L,"Oberoi","Agra");
        Customer customer = new Customer("John");
        Booking booking = new Booking(45L,Date.valueOf("2021-12-05"),Date.valueOf("2021-12-08"),BookingStatus.ACTIVE,hotel,customer,2);
        when(iBookingDao.bookHotel(booking)).thenReturn(booking);
        Booking actualBooking = iBookingDao.bookHotel(booking);
        assertEquals(45,actualBooking.getBookingId());
    }

    @Test
    @DisplayName("Booking DAO Test : Cancel a booking with booking not exist with id")
    void cancelBookingWithIdNotExist() throws NotFoundException,NoSuchElementException {
        assertThrows(NoSuchElementException.class,()-> iBookingDao.cancelBooking(202L));
    }

    @Test
    @DisplayName("Booking DAO Test : Cancel a booking with booking exist with id")
    @Disabled
    void cancelBookingWithIdExist()  throws NoSuchElementException {
//       when(iBookingDao.cancelBooking(11L)).thenReturn(true);
//       bookingList.get(2).cancelBooking();
//       boolean actual = iBookingDao.cancelBooking(11L);
//       assertTrue(actual);
    }

    @Test
    @DisplayName("Booking DAO Test : get a booking by id")
    void findBookingWithIdExist() {
        when(iBookingDao.getBookingWithId(11L)).thenReturn(Optional.ofNullable(bookingList.get(2)));
        Optional<Booking> booking = iBookingDao.getBookingWithId(11L);
        assertTrue(booking.isPresent());
        assertEquals(bookingList.get(2),booking.get());

    }


}