package com.wego.flights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wego.flights.constants.ParkingConstants;
import com.wego.flights.entity.Parking;
import com.wego.flights.service.CarParkService;

/**
 * Parking controller to update and 
 * conditionally fetch the parking data
 * 
 * @author sumit kumar
 *
 */
@RestController
public class CarParkingController {

	@Autowired
	private CarParkService carParkService;

	/**
	 * API to update the parking availability data
	 * 
	 * @return String
	 */
	@PutMapping(ParkingConstants.UPDATE_PARKING_LOT_URL)
	public ResponseEntity<String> getCarParkingAvailability() {
		carParkService.fetchAndUpdateCarParkAvailability();
		return new ResponseEntity<String>(ParkingConstants.SUCCESSFULLY_UPDATED, HttpStatus.OK);
	}
	
	/**
	 * API to fetch the nearest data for given latitude and longitude
	 * count of response data is in accordance with page and perPage
	 * 
	 * @param latitude
	 * @param longitude
	 * @param page
	 * @param perPage
	 * @return List<Parking>
	 */
	@GetMapping(ParkingConstants.GET_NEAREST_AVAILABLE_PARKING_URL)
	public ResponseEntity<List<Parking>> getNearestParkingAvailability(
			@RequestParam(name = "latitude", required = true) Double latitude,
			@RequestParam(name = "longitude", required = true) Double longitude,
			@RequestParam(name = "page", required = true) Integer page,
			@RequestParam(name = "per_page", required = true) Integer perPage) {

		List<Parking> results = carParkService.fetchNearestAvailableParking(latitude, longitude, page, perPage);
		return ResponseEntity.ok(results);
	}
}
