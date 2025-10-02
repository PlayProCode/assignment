package com.wego.flights.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wego.flights.entity.CarParkInfo;

/**
 * Data JPA repository to fetch parking data from db
 * @author sumit kumar
 *
 */
public interface ParkingRepository extends JpaRepository<CarParkInfo,String>{

	Optional<CarParkInfo> findByCarParkNo(String carParkNo);

	//LLM see /llm/prompts.md#p2
	//LLM see /llm/prompts.md#p6
	@Query(value = """
	        SELECT *, (
	            6371 * acos(
	                cos(radians(:yCord)) *
	                cos(radians(CAST(y_cord AS DOUBLE))) *
	                cos(radians(CAST(x_cord AS DOUBLE)) - radians(:xCord)) +
	                sin(radians(:yCord)) *
	                sin(radians(CAST(y_cord AS DOUBLE)))
	            )
	        ) AS distance
	        FROM car_park_info WHERE lots_available >0
	        ORDER BY distance
	        LIMIT :limit OFFSET :offset  
	        """, nativeQuery = true)
	    List<CarParkInfo> findNearestCarParks(
	        @Param("xCord") double xCord,
	        @Param("yCord") double yCord,
	        @Param("limit") int page,
	        @Param("offset") int offset
	    );
}