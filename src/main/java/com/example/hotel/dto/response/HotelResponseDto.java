package com.example.hotel.dto.response;

import com.example.hotel.model.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponseDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("city")
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void convertToHotelResponse(Hotel hotel){
        setCity(hotel.getCity());
        setId(hotel.getId());
        setName(hotel.getName());
    }
}
