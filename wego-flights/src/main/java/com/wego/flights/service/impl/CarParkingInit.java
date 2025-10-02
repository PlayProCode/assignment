package com.wego.flights.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wego.flights.entity.CarParkInfo;
import com.wego.flights.repository.ParkingRepository;
import com.wego.flights.utills.CoordinatesConvertorUtil;
/**
 * CarParkingInit class is made to initialize and feed the static data
 * when application loaded 1st time
 * 
 * @author Sumit Kumar
 *
 */
@Configuration
public class CarParkingInit {

	//LLM see /llm/prompts.md#p1
	@Bean
	CommandLineRunner loadData(ParkingRepository repository) {
		return args -> {
			//if data is not there then only static data is loaded
			if (repository.findAll().size() != 0)
				return;
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/static/HDBCarparkInformation.csv")))) {
				String line;
				reader.readLine(); // skip header
				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(",");
					String[] coordinates = CoordinatesConvertorUtil.coordinateConverted(fields[2], fields[3]);
					CarParkInfo carParkInfo = new CarParkInfo();
					carParkInfo.setCarParkNo(fields[0]);
					carParkInfo.setAddress(fields[1]);
					carParkInfo.setXCord(coordinates[0]);
					carParkInfo.setYCord(coordinates[1]);
					carParkInfo.setCarParkType(fields[4]);
					carParkInfo.setTypeOfParkingSystem(fields[5]);
					carParkInfo.setShortTermParking(fields[6]);
					carParkInfo.setFreeParking(fields[7]);
					carParkInfo.setNightParking(fields[8]);
					carParkInfo.setCarParkingDecks(fields[9]);
					carParkInfo.setGantryHeight(fields[10]);
					carParkInfo.setCarParkBasement(fields[11]);
					repository.save(carParkInfo);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

}
