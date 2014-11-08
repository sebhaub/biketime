package org.wahlzeit.location;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

/**
 * Created by sebi on 07.11.14.
 */
public class GPSLocation extends AbstractLocation {

    private double latitude = 0;
    private double longitude = 0;

    /***
     * Initializes this GPSLocation with a lat / lon of 0 / 0
     */
    public GPSLocation(){
        this(0,0);
    }

    /***
     * Initializes this GPSLocation with the given lat / lon
     * @param latitude
     * @param longitude
     */
    public GPSLocation(double latitude, double longitude){
        assertIsValidLatitude(latitude);
        assertIsValidLongitude(longitude);
        doSetLocation(latitude,longitude);
        this.isLocationSet = true;
    }

    public GPSLocation(String mapcode){
        assertIsValidMapcodeString(mapcode);
        doSetLocation(mapcode);
    }

    @Override
    public String asString() {
        return ("{lat=["+latitude+"] lon=["+longitude+"]}");
    }

    @Override
    protected void doSetLocation(String mapcode) {
        try {
            Point locPoint = MapcodeCodec.decode(mapcode);
            this.latitude = locPoint.getLatDeg();
            this.longitude = locPoint.getLonDeg();
        } catch (UnknownMapcodeException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doSetLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected double doGetLongitude() {
        return this.longitude;
    }

    @Override
    protected double doGetLatitude() {
        return this.latitude;
    }

    @Override
    protected String doGetMapcodeString() {
        return MapcodeCodec.encodeToInternational(latitude,longitude).getMapcode();
    }


}
