package com.example.hotel.controller;

import com.example.hotel.dto.response.APIResponse;
import com.example.hotel.dto.response.BookingResponseDto;
import com.example.hotel.model.Booking;
import com.example.hotel.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;

    @GetMapping("/user/{id}/booking")
    ResponseEntity<APIResponse<List<BookingResponseDto>>> getAllBookings(@PathVariable("id") Long id){
        APIResponse<List<BookingResponseDto>> response = new APIResponse<>();
        List<BookingResponseDto> responseData = new ArrayList<>();
        try{
            List<Booking> list = iCustomerService.getAllBookings(id);
            for(Booking b: list){
                BookingResponseDto data = new BookingResponseDto();
                data.convertToBookingResponse(b);
                responseData.add(data);
            }
            response.setData(responseData);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        response.setMessage("Internal Server Error");
        response.setStatus("failure");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
