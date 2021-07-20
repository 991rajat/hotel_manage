package com.example.hotel.dto.requestDTO;

import java.util.Date;

public class BookingRequestDTO {
    private Long hotel_id;
    private Date date_from;
    private Date date_to;
    private int no_of_rooms;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long hotel_id, Date date_from, Date date_to, int no_of_rooms) {
        this.hotel_id = hotel_id;
        this.date_from = date_from;
        this.date_to = date_to;
        this.no_of_rooms = no_of_rooms;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }
}
