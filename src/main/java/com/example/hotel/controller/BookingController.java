package com.example.hotel.controller;

import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.service.IBookingService;
import com.example.hotel.dto.response.APIResponse;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class BookingController {

    @Autowired
    private IBookingService iBookingService;

    // @Desc : Get All bookings
    @GetMapping("/booking")
    ResponseEntity<APIResponse<List<BookingResponseDto>>> getAllBookings(){
        final APIResponse<List<BookingResponseDto>> response = new APIResponse<>();
        final List<BookingResponseDto> bookingResponseList = iBookingService.getAllBookings();
        response.setData(bookingResponseList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // @Desc : Get a booking with particular id
    @GetMapping("/booking/{id}")
    ResponseEntity<APIResponse<BookingResponseDto>> getBooking(@Valid @NotNull @PathVariable("id")final Long bookingId){
        final APIResponse<BookingResponseDto> response = new APIResponse<>();
        final BookingResponseDto bookingResponse = iBookingService.getBookingWithId(bookingId);
        response.setData(bookingResponse);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    // @Desc : Booking a hotel
    @PostMapping("/booking")
    ResponseEntity<APIResponse<BookingResponseDto>> bookHotel(@Valid @RequestBody final BookingRequestDto bookingRequest) {
       final APIResponse<BookingResponseDto> response = new APIResponse<>();
       final BookingResponseDto booking = iBookingService.bookingHotel(bookingRequest);
       response.setData(booking);
       return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    // @Desc : Cancel the booking with particular id.
    @DeleteMapping("/booking/{id}")
    ResponseEntity<APIResponse<BookingResponseDto>> cancelBooking(@Valid @PathVariable("id") @NotNull final Long bookingId){
        final APIResponse<BookingResponseDto> response = new APIResponse<>();
        final boolean status = iBookingService.cancelBooking(bookingId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
