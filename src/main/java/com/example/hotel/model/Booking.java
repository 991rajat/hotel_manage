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
    @Column(name = "arriving_date")
    private Date arrivingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departureDate;

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

    public Booking(Long id, Date arrivingDate, Date departureDate, BookingStatus status, Hotel hotel, Customer customer, Integer bookedRooms) {
        this.id = id;
        this.arrivingDate = arrivingDate;
        this.departureDate = departureDate;
        this.status = status;
        this.hotel = hotel;
        this.customer = customer;
        this.bookedRooms = bookedRooms;
    }

    public Booking(Date arrivingDate, Date departureDate, Hotel hotel, Customer customer, Integer bookedRooms) {
        this.arrivingDate = arrivingDate;
        this.departureDate = departureDate;
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
                ", arrivingDate=" + arrivingDate +
                ", departureDate=" + departureDate +
                ", hotelId=" + hotel.getId() +
                ", bookedRooms=" + bookedRooms +
                '}';
    }
}
