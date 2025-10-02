package com.wego.flights.constants;

/**
 * Constant file to fetch constants
 * 
 * @author sumit Kumar
 *
 */
public class ParkingConstants {
	
	//Generic Constants
	public static final String COORDINATE_REFERENCE_SVY21 = "EPSG:3414";
	public static final String COORDINATE_REFERENCE_WGS84 = "EPSG:4326";
	
	//URIs
	public static final String PARKING_AVAILABLE_API_URL = "https://api.data.gov.sg/v1/transport/carpark-availability";
	public static final String UPDATE_PARKING_LOT_URL = "/update-parking";
	public static final String GET_NEAREST_AVAILABLE_PARKING_URL = "/carparks/nearest";
	
	//API Response Constants
	public static final String SUCCESSFULLY_UPDATED = "SUCCESSFULLY UPDATED !";
	public static final String ERROR = "error";
	public static final String ERROR_MESSAGE = "message";
	public static final String INTERNAL_SERVER_ERROR = "INTENAL_SERVER_ERROR";
	public static final String MISSING_PARAM = "Missing required parameter: ";
	public static final String INVALID_PARAM = "Invalid parameter: ";
}
