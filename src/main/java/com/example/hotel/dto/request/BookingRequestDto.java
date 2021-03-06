package com.example.hotel.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequestDto {

    @JsonProperty("hotel_id")
    private Long hotelId;

    @JsonProperty("arriving_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Valid
    private Date arrivingDate;

    @JsonProperty("departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Valid
    private Date departureDate;


    @JsonProperty("no_of_rooms")
    private int noOfRooms;

    @JsonProperty("customer_id")
    private Long customerId;

    public BookingRequestDto() {
    }

    public BookingRequestDto(Long hotelId, Date dateFrom, Date dateTo, int noOfRooms, Long customerId) {
        this.hotelId = hotelId;
        this.arrivingDate = dateFrom;
        this.departureDate = dateTo;
        this.noOfRooms = noOfRooms;
        this.customerId = customerId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public Date getArrivingDate() {
        return arrivingDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public Long getCustomerId() {
        return customerId;
    }



}
