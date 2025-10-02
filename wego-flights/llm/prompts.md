#p1 load data when application starts using command line runner
To load data when your Spring Boot application starts, you can use a CommandLineRunner bean. This is ideal for initializing your database with static files like CSVs or performing setup tasks.

#p2 I have latitude and longitude in my mysql db. provide a jpa query to fetch given count of records nearest to given latitude and longitude
Native JPA Query Using Haversine Formula
Assuming your entity is called Location and it has latitude and longitude fields:

java
@Query(value = """
    SELECT *, 
           (6371 * acos(
               cos(radians(:lat)) * cos(radians(latitude)) *
               cos(radians(longitude) - radians(:lng)) +
               sin(radians(:lat)) * sin(radians(latitude))
           )) AS distance
    FROM location
    ORDER BY distance
    LIMIT :count
    """, nativeQuery = true)
List<Location> findNearestLocations(@Param("lat") double latitude,
                                    @Param("lng") double longitude,
                                    @Param("count") int count);
🧮 Explanation
6371 is the Earth's radius in kilometer



#p3 convert from SVY21. to longitude and latitude in Java
To convert coordinates from SVY21 (Singapore's projected coordinate system) to geographic coordinates (latitude and longitude), you need to apply a mathematical transformation using the parameters of the SVY21 system.
Add Proj4J to your project. If you're using Maven:

xml
<dependency>
  <groupId>org.locationtech.proj4j</groupId>
  <artifactId>proj4j</artifactId>
  <version>1.1.0</version>
</dependency>
CRSFactory crsFactory = new CRSFactory();

Define SVY21 and WGS84 systems
        CoordinateReferenceSystem svy21 = crsFactory.createFromName("EPSG:3414");
        CoordinateReferenceSystem wgs84 = crsFactory.createFromName("EPSG:4326");

Create a transform from SVY21 to WGS84
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(svy21, wgs84);

Input SVY21 coordinates (Easting, Northing)
        ProjCoordinate svyCoord = new ProjCoordinate(28001.642, 38744.572);
        ProjCoordinate latLonCoord = new ProjCoordinate();

Perform the transformation
        transform.transform(svyCoord, latLonCoord);


#p4 latest lombok dependency for springBoot App

	<dependency>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok</artifactId>
	    <version>1.18.30</version>
	    <scope>provided</scope>
	</dependency>

#p5 @ExceptionHandler sample for springBoot

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

// Handle specific exception
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException ex) {
        return new ResponseEntity<>("Cannot perform arithmetic operation: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

// Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

#p6 limit with offset mysql jpa @query example
  
@Query(value = "SELECT * FROM employees ORDER BY employee_id LIMIT :limit OFFSET :offset", nativeQuery = true)
   