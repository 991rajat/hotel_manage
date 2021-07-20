package com.example.hotel.repository;

import com.example.hotel.model.Hotel;
import com.example.hotel.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class ISearchRepositoryTest {

    @Autowired
    private ISearchRepository iSearchRepository;

    @Test
    void findHotelWithCity() {
        Hotel hotel1 = new Hotel(1L,"Marriott","Delhi");
        Room room11 = new Room(hotel1,true);
        Room room12 = new Room(hotel1,true);
        Room room13 = new Room(hotel1,true);
        Room room14 = new Room(hotel1,true);
        hotel1.setRoomList(Arrays.asList(room11,room12,room13,room14));

        Hotel hotel2 = new Hotel(2L,"Taj","Delhi");
        Room room21 = new Room(hotel2,true);
        Room room22 = new Room(hotel2,true);
        Room room23 = new Room(hotel2,true);
        Room room24 = new Room(hotel2,true);
        hotel2.setRoomList(Arrays.asList(room21,room22,room23,room24));

        Hotel hotel3 = new Hotel(3L,"Radisson","Banglore");
        Room room31 = new Room(hotel3,true);
        Room room32 = new Room(hotel3,true);
        Room room33 = new Room(hotel3,true);
        Room room34 = new Room(hotel3,true);
        hotel3.setRoomList(Arrays.asList(room31,room32,room33,room34));
        Hotel hotel4 = new Hotel(4L,"Oberoi","Jodhpur");
        Room room41 = new Room(hotel4,true);
        Room room42 = new Room(hotel4,true);
        Room room43 = new Room(hotel4,true);
        Room room44 = new Room(hotel4,true);
        hotel4.setRoomList(Arrays.asList(room41,room42,room43,room44));
        hotel1 = iSearchRepository.save(hotel1);
        hotel2 = iSearchRepository.save(hotel2);
        iSearchRepository.save(hotel3);
        iSearchRepository.save(hotel4);

        List<Hotel> expected = new ArrayList<>();
        expected.add(hotel1);
        expected.add(hotel2);
        Optional<List<Hotel>> actual = iSearchRepository.findHotelWithCity("Delhi");

        Assertions.assertEquals(expected,actual.get());

    }
}