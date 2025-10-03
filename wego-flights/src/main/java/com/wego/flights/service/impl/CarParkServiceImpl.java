package com.wego.flights.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wego.flights.constants.ParkingConstants;
import com.wego.flights.entity.CarParkInfo;
import com.wego.flights.entity.Parking;
import com.wego.flights.repository.ParkingRepository;
import com.wego.flights.service.CarParkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarParkServiceImpl implements CarParkService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ParkingRepository parkingRepository;

	@Override
	public void fetchAndUpdateCarParkAvailability() {
		ResponseEntity<String> response = restTemplate.getForEntity(ParkingConstants.PARKING_AVAILABLE_API_URL,
				String.class);
		log.info("data fetched for available lots !");
		JSONObject json = new JSONObject(response.getBody());

		JSONArray carparkData = json.getJSONArray("items").getJSONObject(0).getJSONArray("carpark_data");

		for (int i = 0; i < carparkData.length(); i++) {
			JSONObject carpark = carparkData.getJSONObject(i);
			String carParkNo = carpark.getString("carpark_number");
			String updateDatetime = carpark.getString("update_datetime");

			JSONArray carparkInfoArray = carpark.getJSONArray("carpark_info");
			Optional<CarParkInfo> optionalInfo = parkingRepository.findByCarParkNo(carParkNo);
			JSONObject infoObj = carparkInfoArray.getJSONObject(0);
			String lotType = infoObj.getString("lot_type");
			String totalLots = infoObj.getString("total_lots");
			String lotsAvailable = infoObj.getString("lots_available");

			if (optionalInfo.isPresent()) {
				if (optionalInfo.isPresent()) {
					CarParkInfo carParkAvail = optionalInfo.get();
					carParkAvail.setTotalLots(totalLots);
					carParkAvail.setLotsAvailable(lotsAvailable);
					carParkAvail.setUpdatedAt(updateDatetime);
					carParkAvail.setLotsType(lotType);
					parkingRepository.save(carParkAvail);
				} else {
					CarParkInfo newInfo = new CarParkInfo();
					newInfo.setLotsType(lotType);
					newInfo.setTotalLots(totalLots);
					newInfo.setLotsAvailable(lotsAvailable);
					newInfo.setCarParkNo(carParkNo);
					newInfo.setUpdatedAt(updateDatetime);
					parkingRepository.save(newInfo);
				}
			}
		}
	}

	@Override
	public List<Parking> fetchNearestAvailableParking(Double latitude, Double longitude, Integer page,
			Integer perPage) {
		List<CarParkInfo> carParInfos = parkingRepository.findNearestCarParks(latitude, longitude, perPage,
				(page-1) * perPage);
		log.info("carParInfos received from db !");
		List<Parking> parkings = new ArrayList<Parking>();
		for (CarParkInfo cpf : carParInfos)
			parkings.add(new Parking(cpf.getAddress(), Double.parseDouble(cpf.getXCord()),
					Double.parseDouble(cpf.getYCord()), Integer.parseInt(cpf.getTotalLots()),
					Integer.parseInt(cpf.getLotsAvailable())));
		log.info("nerest data received !");
		return parkings;
	}
}
