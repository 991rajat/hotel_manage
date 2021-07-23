package com.example.hotel.controller;

import com.example.hotel.dto.request.BookingRequestDto;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;
import com.example.hotel.service.IBookingService;
import com.example.hotel.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private IBookingService iBookingService;

    @GetMapping("/booking")
    ResponseEntity<APIResponse<List<BookingResponseDto>>> getAllBookings(){
        APIResponse<List<BookingResponseDto>> response = new APIResponse<>();
        List<BookingResponseDto> responseData = new ArrayList<>();
        try{
            List<Booking> list = iBookingService.getAllBookings();
            for(Booking b: list){
                BookingResponseDto data = new BookingResponseDto();
                data.convertToBookingResponse(b);
                responseData.add(data);
            }
            response.setData(responseData);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(Exception e){

        }
        response.setMessage("Internal Server Error");
        response.setStatus("failure");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/booking/{id}")
    ResponseEntity<APIResponse<BookingResponseDto>> getBooking(@PathVariable("id") Long bookingId){
        APIResponse<BookingResponseDto> response = new APIResponse<>();
        try{
            Booking booking = iBookingService.getBookingWithId(bookingId);
            BookingResponseDto data = new BookingResponseDto();
            data.convertToBookingResponse(booking);
            response.setData(data);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(Exception e){
            if(e instanceof ResourceNotFoundException)
            {
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
        }
        response.setMessage("Internal Server Error");
        response.setStatus("failure");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/booking")
    ResponseEntity<APIResponse<BookingResponseDto>> bookHotel(@RequestBody BookingRequestDto bookingRequest){
       APIResponse<BookingResponseDto> response = new APIResponse<>();

        try{
            Booking booking = iBookingService.bookingHotel(bookingRequest);
            BookingResponseDto data = new BookingResponseDto();
            data.convertToBookingResponse(booking);
            response.setData(data);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(Exception e){
            if(e instanceof ResourceNotFoundException){
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

    @DeleteMapping("/booking/{id}")
    ResponseEntity<APIResponse<BookingResponseDto>> cancelBooking(@PathVariable("id") Long bookingId){
        APIResponse<BookingResponseDto> response = new APIResponse<>();
        try{
            boolean status = iBookingService.cancelBooking(bookingId);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(Exception e){

        }
        response.setMessage("Internal Server Error");
        response.setStatus("failure");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
