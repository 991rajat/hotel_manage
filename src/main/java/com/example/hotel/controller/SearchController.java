package com.example.hotel.controller;

import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.service.ISearchService;
import com.example.hotel.dto.response.APIResponse;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private ISearchService iSearchService;

    // @Desc : Get all the hotels.
    @GetMapping(value = "/")
    ResponseEntity<APIResponse<List<HotelResponseDto>>> getAllHotels(){
        APIResponse<List<HotelResponseDto>> response = new APIResponse<>();
        try{
            final List<HotelResponseDto> hotelResponseList = iSearchService.getAllHotels();
            response.setData(hotelResponseList);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(Exception e){
            response.setStatus("failure");
            response.setMessage("Internal Server Error.");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // @Desc : Get all the hotels with particular city within particular arriving and departure date.
    @GetMapping(value = "/search")
    ResponseEntity<APIResponse<List<HotelResponseDto>>> getAllHotelsWithDate(@RequestParam(name = "city") @NotNull final String city,
                                                                             @RequestParam(name = "arriving_date") @NotNull @DateTimeFormat(pattern="yyyy-mm-dd") final Date arrivingDate,
                                                                             @RequestParam(name = "departure_date") @NotNull @DateTimeFormat(pattern="yyyy-mm-dd") final Date departureDate){


        APIResponse<List<HotelResponseDto>> response = new APIResponse<>();
        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateFrom = formatter.parse(arrivingDate);
//            Date dateTo = formatter.parse(departureDate);
            List<HotelResponseDto> hotelResponseList = iSearchService.getAllHotelsWithDate(city,arrivingDate,departureDate);
            response.setData(hotelResponseList);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e){
            if(e instanceof ParseException) {
                response.setMessage("Date not formatted");
                response.setStatus("failure");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        response.setStatus("failure");
        response.setMessage("Internal Server Error.");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);


    }

}
