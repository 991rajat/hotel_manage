package com.example.hotel.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "id" , updatable = false, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name = "available")
    private boolean available;

    public Room() {
    }

    public Room(Hotel hotel, boolean available) {
        this.hotel = hotel;
        this.available = available;
    }

    public Room(Long id, Hotel hotel, boolean available) {
        this.id = id;
        this.hotel = hotel;
        this.available = available;
    }

    public Long getRoomId() {
        return id;
    }

    public void setRoomId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id) && hotel.equals(room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotel);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", available=" + available +
                '}';
    }
}
