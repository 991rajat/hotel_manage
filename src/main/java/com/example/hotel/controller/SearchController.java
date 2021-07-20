package com.example.hotel.controller;

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
        ResponseEntity<APIResponse<List<Hotel>>> getAllHotels(){
                List<Hotel> list = new ArrayList<>();
                APIResponse<List<Hotel>> resp = new APIResponse<>();
                try{
                        list = iSearchService.getAllHotels();
                        resp.setData(list);
                        return new ResponseEntity<APIResponse<List<Hotel>>>(resp, HttpStatus.OK);

                }catch(Exception e){

                }
                resp.setStatus("failure");
                return new ResponseEntity<APIResponse<List<Hotel>>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);

        }

        @GetMapping(value = "/search")
        ResponseEntity<APIResponse<List<Hotel>>> getAllHotelsWithDate(@RequestParam(name = "city")String city,
                                                           @RequestParam(name = "checkIn") String checkIn,
                                                           @RequestParam(name = "checkOut") String checkOut){

                List<Hotel> list = new ArrayList<>();
                APIResponse<List<Hotel>> resp = new APIResponse<>();

                try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date_from = formatter.parse(checkIn);
                        Date date_to = formatter.parse(checkOut);
                        list = iSearchService.getAllHotelsWithDate(city,date_from,date_to);
                        resp.setData(list);
                        return new ResponseEntity<APIResponse<List<Hotel>>>(resp,HttpStatus.OK);
                }
                catch (Exception e){
                        if(e instanceof ParseException) {
                                resp.setMessage("Date not formatted");
                                resp.setStatus("failure");
                                return new ResponseEntity<APIResponse<List<Hotel>>>(resp, HttpStatus.BAD_REQUEST);
                        }
                        if(e instanceof ResourceNotFoundException){
                                resp.setMessage(e.getMessage());
                                resp.setStatus("failure");
                                return new ResponseEntity<APIResponse<List<Hotel>>>(resp, HttpStatus.NOT_FOUND);

                        }
                }
                resp.setStatus("failure");
                return new ResponseEntity<APIResponse<List<Hotel>>>(resp,HttpStatus.OK);


        }

}
