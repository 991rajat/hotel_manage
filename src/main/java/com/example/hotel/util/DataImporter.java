package com.example.hotel.util;

import com.example.hotel.model.Booking;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.Room;
import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ISearchRepository;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DataImporter {

    public void insertData(ISearchRepository searchRepository,IBookingRepository iBookingRepository){

        Hotel hotel1 = new Hotel(1L,"Marriott","Delhi");
        Room room11 = new Room(hotel1,true);
        Room room12 = new Room(hotel1,true);
        Room room13 = new Room(hotel1,true);
        Room room14 = new Room(hotel1,true);
        hotel1.setRoomList(Arrays.asList(room11,room12,room13,room14));

        Hotel hotel2 = new Hotel(2L,"Taj","Mumbai");
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
        hotel1 = searchRepository.save(hotel1);
        hotel2 = searchRepository.save(hotel2);
        hotel3 = searchRepository.save(hotel3);
        hotel4 = searchRepository.save(hotel4);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Booking booking1 = new Booking(format.parse("2021-02-12"), format.parse("2021-02-17"), hotel1.getHotelId(), 1);
            Booking booking2 = new Booking(format.parse("2021-02-16"), format.parse("2021-02-20"), hotel1.getHotelId(), 2);
            Booking booking3 = new Booking(format.parse("2021-06-01"), format.parse("2021-06-06"), hotel2.getHotelId(), 3);
            Booking booking4 = new Booking(format.parse("2021-04-12"), format.parse("2021-05-17"), hotel3.getHotelId(), 2);
            iBookingRepository.save(booking1);
            iBookingRepository.save(booking2);
            iBookingRepository.save(booking3);
            iBookingRepository.save(booking4);
        }catch(Exception e){

        }

    }



    public void deleteAll(ISearchRepository bean, IBookingRepository bean1) {
        bean.deleteAll();
        bean1.deleteAll();
    }
}
