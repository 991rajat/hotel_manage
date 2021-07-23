package com.example.hotel.controller;

import com.example.hotel.dto.response.HotelResponseDto;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.Hotel;
import com.example.hotel.service.ISearchService;
import com.example.hotel.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private ISearchService iSearchService;

    @GetMapping(value = "/")
    ResponseEntity<APIResponse<List<HotelResponseDto>>> getAllHotels(){
        List<HotelResponseDto> responseData = new ArrayList<>();
        APIResponse<List<HotelResponseDto>> response = new APIResponse<>();
        try{
            List<Hotel> list= iSearchService.getAllHotels();
            for(Hotel h: list) {
                HotelResponseDto data = new HotelResponseDto();
                data.convertToHotelResponse(h);
                responseData.add(data);
            }
            response.setData(responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(Exception e){

        }
        response.setStatus("failure");
        response.setMessage("Internal Server Error.");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/search")
    ResponseEntity<APIResponse<List<HotelResponseDto>>> getAllHotelsWithDate(@RequestParam(name = "city")String city,
                                                                             @RequestParam(name = "arriving_date") String arrivingDate,
                                                                             @RequestParam(name = "departure_date") String departureDate){


        APIResponse<List<HotelResponseDto>> response = new APIResponse<>();
        List<HotelResponseDto> responseData = new ArrayList<>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = formatter.parse(arrivingDate);
            Date dateTo = formatter.parse(departureDate);
            List<Hotel> list = iSearchService.getAllHotelsWithDate(city,dateFrom,dateTo);
            for(Hotel h: list) {
                HotelResponseDto data = new HotelResponseDto();
                data.convertToHotelResponse(h);
                responseData.add(data);
            }
            response.setData(responseData);
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
