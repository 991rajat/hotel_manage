package com.example.hotel.controller;

import com.example.hotel.dto.requestDTO.BookingRequestDTO;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import com.example.hotel.model.Booking;
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

    @GetMapping("/book")
    ResponseEntity<APIResponse<List<Booking>>> getAllBookings(){
        APIResponse<List<Booking>> apiResponse = new APIResponse<>();
        try{
            List<Booking> list = iBookingService.getAllBookings();
            apiResponse.setData(list);
            return new ResponseEntity<APIResponse<List<Booking>>>(apiResponse,HttpStatus.CREATED);
        }catch(Exception e){

        }
        apiResponse.setMessage("Internal Server Error");
        apiResponse.setStatus("failure");
        return new ResponseEntity<APIResponse<List<Booking>>>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/book")
    ResponseEntity<APIResponse<Booking>> bookHotel(@RequestBody BookingRequestDTO bookingRequest){
        APIResponse<Booking> apiResponse = new APIResponse<>();
        try{
            Booking booking = iBookingService.bookingHotel(bookingRequest);
            apiResponse.setData(booking);
            return new ResponseEntity<APIResponse<Booking>>(apiResponse,HttpStatus.CREATED);
        }catch(Exception e){
            if(e instanceof ResourceNotFoundException){
                apiResponse.setStatus("failure");
                apiResponse.setMessage(e.getMessage());
                return new ResponseEntity<APIResponse<Booking>>(apiResponse,HttpStatus.NOT_FOUND);
            }
        }
        apiResponse.setMessage("Internal Server Error");
        apiResponse.setStatus("failure");
        return new ResponseEntity<APIResponse<Booking>>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/book/{id}")
    ResponseEntity<APIResponse<Booking>> cancelBooking(@PathVariable("id") Long bookingId){
        APIResponse<Booking> apiResponse = new APIResponse<>();
        try{
            boolean booking = iBookingService.cancelBooking(bookingId);
            return new ResponseEntity<APIResponse<Booking>>(apiResponse,HttpStatus.OK);
        }catch(Exception e){

        }
        apiResponse.setMessage("Internal Server Error");
        apiResponse.setStatus("failure");
        return new ResponseEntity<APIResponse<Booking>>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
