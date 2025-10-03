package com.wego.flights.utills;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

import com.wego.flights.constants.ParkingConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Class file for svy to wgs converter (latitude and longitude convertion)
 *
 * @author sumit kumar
 *
 */
@Slf4j
public class CoordinatesConvertorUtil {

	//LLM see /llm/prompts.md#p3

	public static String[] coordinateConverted(String x, String y) {
		CRSFactory crsFactory = new CRSFactory();
		log.info("coordinates received x{}, y{}", x,y);
        CoordinateReferenceSystem svy21 = crsFactory.createFromName(ParkingConstants.COORDINATE_REFERENCE_SVY21);
        CoordinateReferenceSystem wgs84 = crsFactory.createFromName(ParkingConstants.COORDINATE_REFERENCE_WGS84);

        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(svy21, wgs84);

        ProjCoordinate svyCoord = new ProjCoordinate(Double.parseDouble(x),Double.parseDouble(y));
        ProjCoordinate latLonCoord = new ProjCoordinate();

        transform.transform(svyCoord, latLonCoord);
        log.info("transformation done");
        String[] arr=new String[2];
        arr[0]=""+latLonCoord.x;
        arr[1]=""+latLonCoord.y;
        return arr;
    }
}
