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

    @Override
    public List<Hotel> findAllHotels() {
        return iSearchRepository.findAll();
    }

    @Override
    public List<Hotel> findAllHotelsWithCity(String city) {
        return iSearchRepository.findHotelWithCity(city).get();
    }

    @Override
    public Optional<Hotel> findHotelById(Long id) {
        return iSearchRepository.findById(id);
    }
}
