package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    private Long bookingId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @JsonFormat(pattern = "yy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    private Long hotelId;
    private Integer bookedRooms;

    public Booking() {
    }

    public Booking(Date dateFrom, Date dateTo, Long hotelId, Integer bookedRooms) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.hotelId = hotelId;
        this.bookedRooms = bookedRooms;
    }

    public Booking(Long bookingId, Date dateFrom, Date dateTo, Long hotelId, Integer bookedRooms) {
        this.bookingId = bookingId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.hotelId = hotelId;
        this.bookedRooms = bookedRooms;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(Integer bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", hotelId=" + hotelId +
                ", bookedRooms=" + bookedRooms +
                '}';
    }
}
