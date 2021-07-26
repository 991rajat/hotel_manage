package com.example.hotel.dao;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.ISearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchDaoImpl implements ISearchDao{

    @Autowired
    private ISearchRepository iSearchRepository;

    //@Desc :  Get list of all hotels.
    @Override
    public List<Hotel> findAllHotels() {
        return iSearchRepository.findAll();
    }

    //@Desc : Get list of all hotels with a given city.
    @Override
    public List<Hotel> findAllHotelsWithCity(String city) {
        return iSearchRepository.findByCity(city);
    }

    //@Desc : Get a hotel with a given id.
    @Override
    public Optional<Hotel> findHotelById(Long id) {
        return iSearchRepository.findById(id);
    }
}
