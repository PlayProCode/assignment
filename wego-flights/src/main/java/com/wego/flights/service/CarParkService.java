package com.wego.flights.service;

import java.util.List;

import com.wego.flights.entity.Parking;

/**
 * Car Park service for service methods
 * @author sumit kumar
 *
 */
public interface CarParkService {

	/* method to update car parking availablity
	 * internally api is called to update the internal db 
	 * for available parking lots
	 */
	public void fetchAndUpdateCarParkAvailability();

	/*method for getting the nearest available parking data
	 * in accordance to page and perpage data
	 */
	public List<Parking> fetchNearestAvailableParking(Double latitude, Double longitude, Integer page, Integer perPage);

}
