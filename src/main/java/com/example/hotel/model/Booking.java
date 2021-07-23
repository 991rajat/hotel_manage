package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "id" , updatable = false, nullable = false)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_from")
    private Date dateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_to")
    private Date dateTo;

    @Column(name = "status")
    private BookingStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id" ,referencedColumnName = "id")
    private Customer customer;

    @Column(name = "booked_rooms")
    private Integer bookedRooms;

    public Booking() {
    }

    public Booking(Long id, Date dateFrom, Date dateTo, BookingStatus status, Hotel hotel, Customer customer, Integer bookedRooms) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
        this.hotel = hotel;
        this.customer = customer;
        this.bookedRooms = bookedRooms;
    }

    public Booking(Date dateFrom, Date dateTo, Hotel hotel, Customer customer, Integer bookedRooms) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.hotel = hotel;
        this.customer = customer;
        this.bookedRooms = bookedRooms;
        this.status = BookingStatus.ACTIVE;
    }

    public Long getBookingId() {
        return id;
    }

    public void setBookingId(Long id) {
        this.id = id;
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

    public Integer getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(Integer bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public void cancelBooking(){
        this.status = BookingStatus.CANCELED;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", hotelId=" + hotel.getId() +
                ", bookedRooms=" + bookedRooms +
                '}';
    }
}
