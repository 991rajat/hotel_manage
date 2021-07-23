package com.example.hotel.repository;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.model.Booking;
import com.example.hotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT new com.example.hotel.model.Booking(b.id,b.dateFrom,b.dateTo,b.status,b.hotel,b.customer,b.bookedRooms) FROM Booking b where b.customer.id=?1" )
    List<Booking> getAllBookings(@Param("id")Long id);
}
