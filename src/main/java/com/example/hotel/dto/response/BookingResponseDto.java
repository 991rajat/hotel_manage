package com.example.hotel.dto.response;

import com.example.hotel.model.Booking;
import com.example.hotel.model.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("arriving_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arrivingDate;

    @JsonProperty("departure_data")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @JsonProperty("status")
    private BookingStatus status;

    @JsonProperty("hotel_id")
    private Long hotelId;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("booked_rooms")
    private Integer bookedRooms;

    public BookingResponseDto(Long id, Date dateFrom, Date dateTo, BookingStatus status, Long hotelId, Long customerId, Integer bookedRooms) {
        this.id = id;
        this.arrivingDate = dateFrom;
        this.departureDate = dateTo;
        this.status = status;
        this.hotelId = hotelId;
        this.customerId = customerId;
        this.bookedRooms = bookedRooms;
    }

    public BookingResponseDto() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getArrivingDate() {
        return arrivingDate;
    }

    public void setArrivingDate(Date arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(Integer bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public void convertToBookingResponse(Booking booking){
        setId(booking.getBookingId());
        setArrivingDate(booking.getDateFrom());
        setDepartureDate(booking.getDateTo());
        setBookedRooms(booking.getBookedRooms());
        setHotelId(booking.getHotel().getId());
        setCustomerId(booking.getCustomer().getId());
        setStatus(booking.getStatus());

    }
}
