package com.example.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue
    private Long roomId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotelId")
    private Hotel hotel;
    private boolean available;

    public Room() {
    }

    public Room(Hotel hotel, boolean available) {
        this.hotel = hotel;
        this.available = available;
    }

    public Room(Long roomId, Hotel hotel, boolean available) {
        this.roomId = roomId;
        this.hotel = hotel;
        this.available = available;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
        return roomId.equals(room.roomId) && hotel.equals(room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, hotel);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", hotel=" + hotel +
                ", available=" + available +
                '}';
    }
}
