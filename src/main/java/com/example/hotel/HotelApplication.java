package com.example.hotel;

import com.example.hotel.repository.IBookingRepository;
import com.example.hotel.repository.ISearchRepository;
import com.example.hotel.util.DataImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {


		ApplicationContext context= SpringApplication.run(HotelApplication.class, args);
		DataImporter imp = new DataImporter();
		imp.deleteAll(context.getBean(ISearchRepository.class),context.getBean(IBookingRepository.class));
		imp.insertData(context.getBean(ISearchRepository.class),context.getBean(IBookingRepository.class));
	}

}
