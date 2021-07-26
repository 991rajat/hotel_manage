package com.example.hotel.controller;

import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.service.IBookingService;
import com.example.hotel.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private IBookingService iBookingService;

    // @Desc : Get All bookings
    @GetMapping("/booking")
    ResponseEntity<APIResponse<List<BookingResponseDto>>> getAllBookings(){
        APIResponse<List<BookingResponseDto>> response = new APIResponse<>();
        try{
            List<BookingResponseDto> bookingResponseList = iBookingService.getAllBookings();
            response.setData(bookingResponseList);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(Exception e){
            response.setMessage("Internal Server Error");
            response.setStatus("failure");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // @Desc : Get a booking with particular id
    @GetMapping("/booking/{id}")
    ResponseEntity<APIResponse<BookingResponseDto>> getBooking(@PathVariable("id") Long bookingId){
        APIResponse<BookingResponseDto> response = new APIResponse<>();
        try{
            BookingResponseDto bookingResponse = iBookingService.getBookingWithId(bookingId);
            response.setData(bookingResponse);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(NotFoundException e){
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            response.setMessage("Internal Server Error");
            response.setStatus("failure");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @Desc : Booking a hotel
    @PostMapping("/booking")
    ResponseEntity<APIResponse<BookingResponseDto>> bookHotel(@RequestBody BookingRequestDto bookingRequest){
       APIResponse<BookingResponseDto> response = new APIResponse<>();
        try{
            BookingResponseDto booking = iBookingService.bookingHotel(bookingRequest);
            response.setData(booking);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(Exception e){
            if(e instanceof NotFoundException){
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            if(e instanceof RoomNotAvailableException){
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
            }
        }
        response.setMessage("Internal Server Error");
        response.setStatus("failure");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // @Desc : Cancel the booking with particular id.
    @DeleteMapping("/booking/{id}")
    ResponseEntity<APIResponse<BookingResponseDto>> cancelBooking(@PathVariable("id") Long bookingId){
        APIResponse<BookingResponseDto> response = new APIResponse<>();
        try{
            boolean status = iBookingService.cancelBooking(bookingId);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(Exception e){
            response.setMessage("Internal Server Error");
            response.setStatus("failure");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
