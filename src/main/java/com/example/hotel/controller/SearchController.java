package com.example.hotel.controller;

import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.service.ISearchService;
import com.example.hotel.dto.response.APIResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Slf4j
@RestController
public class SearchController {
    @Autowired
    private ISearchService iSearchService;

    // @Desc : Get all the hotels.
    @GetMapping(value = "/")
    ResponseEntity<APIResponse<List<HotelResponseDto>>> getAllHotels(){
        final APIResponse<List<HotelResponseDto>> response = new APIResponse<>();
        final List<HotelResponseDto> hotelResponseList = iSearchService.getAllHotels();
        response.setData(hotelResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    // @Desc : Get all the hotels with particular city within particular arriving and departure date.
    @GetMapping(value = "/search")
    ResponseEntity<APIResponse<List<HotelResponseDto>>> getAllHotelsWithDate(@RequestParam(name = "city") @NotNull @NotEmpty final String city,
                                                                             @RequestParam(name = "arriving_date") @Valid @NotNull @NotEmpty @DateTimeFormat(pattern="yyyy-mm-dd") final Date arrivingDate,
                                                                             @RequestParam(name = "departure_date") @Valid @NotNull @NotEmpty @DateTimeFormat(pattern="yyyy-mm-dd") final Date departureDate) throws MethodArgumentTypeMismatchException {

        final APIResponse<List<HotelResponseDto>> response = new APIResponse<>();
        final List<HotelResponseDto> hotelResponseList = iSearchService.getAllHotelsWithDate(city,arrivingDate,departureDate);
        response.setData(hotelResponseList);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
