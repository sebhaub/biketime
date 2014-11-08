package org.wahlzeit.location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class AllLocationTests {

    protected double latitude = 49.575459;
    protected double longitude = 11.0215239;
    protected double latTwo = 49.019659;
    protected double lonTwo = 11.4903177;
    protected String mapcodeTwo = "VJHJV.3WTX";
    protected String mapcode = "VJFLZ.0X0V";
    double latRounded = (double)Math.round(latitude*1000)/1000;
    double lonRounded = (double)Math.round(longitude*1000)/1000;

    protected Location gpsLocation;
    protected Location mapcodeLocation;

    @Before
    public void setUp() {
        gpsLocation = new GPSLocation(latitude, longitude);
        mapcodeLocation = new MapcodeLocation(latitude,longitude);
    }

    @Test
    public void testMapCodeConstructors(){
        Location mLocMapcode = new MapcodeLocation(latitude, longitude);
        double latVal = mLocMapcode.getLatitude();
        double lonVal = mLocMapcode.getLongitude();
        double latValRounded = (double)Math.round(latVal*1000)/1000;
        double lonValRounded = (double)Math.round(lonVal*1000)/1000;


        assertTrue("Lat should be "+latRounded+" but is "+latValRounded+ " Longitude should be " +
                lonRounded + " but is "+lonValRounded, latValRounded == latRounded && lonValRounded == lonRounded);
        assertTrue("Mapcode is false", mLocMapcode.getAsMapcodeString().equalsIgnoreCase(mapcode));

        Location mLocLatLon = new MapcodeLocation(mapcode);
        latVal = mLocLatLon.getLatitude();
        lonVal = mLocLatLon.getLongitude();
        latValRounded = (double)Math.round(latVal*1000)/1000;
        lonValRounded = (double)Math.round(lonVal*1000)/1000;
        assertTrue("Lat Long is false", latValRounded == latRounded && lonValRounded == lonRounded);
        assertTrue("Mapcode is false", mLocLatLon.getAsMapcodeString().equalsIgnoreCase(mapcode));
    }

    @Test
    public void testGpsConstructors(){
        Location mGpsLocation = new GPSLocation();
        assertTrue("LatLong is false", mGpsLocation.getLatitude() == 0 && mGpsLocation.getLongitude() == 0);
        assertTrue("has location is not set", mGpsLocation.hasLocation());


        mGpsLocation = new GPSLocation(latitude, longitude);
        assertTrue("LatLong is false", mGpsLocation.getLatitude() == latitude && mGpsLocation.getLongitude() == longitude);
        assertTrue("Mapcode is false", mGpsLocation.getAsMapcodeString().equalsIgnoreCase(mapcode));
        assertTrue("has location is not set", mGpsLocation.hasLocation());

        mGpsLocation = new GPSLocation(mapcode);
        double latVal = mGpsLocation.getLatitude();
        double lonVal = mGpsLocation.getLongitude();
        double latValRounded = (double)Math.round(latVal*1000)/1000;
        double lonValRounded = (double)Math.round(lonVal*1000)/1000;
        assertTrue("LatLong is false", latValRounded == latRounded && lonValRounded == lonRounded);
        assertTrue("Mapcode is false", mGpsLocation.getAsMapcodeString().equalsIgnoreCase(mapcode));
    }

    @Test
    public void testGetLatitude(){
        double latVal = gpsLocation.getLatitude();
        assertTrue("Latitude should be "+latitude+" but value is "+latVal, latitude == latVal);
    }

    @Test
    public void testGetLongitude(){
        double lonVal = gpsLocation.getLongitude();
        assertTrue("Longitude should be "+longitude+" but value is "+lonVal, longitude == lonVal);
    }

    @Test
    public void testGetMapcodeOfMapcodeLocation(){
        String mapVal = mapcodeLocation.getAsMapcodeString();

        assertTrue("Mapcode should be "+mapcode+" but is "+mapVal, mapVal.equalsIgnoreCase(mapcode));
    }

    @Test
    public void testGetMapcodeOfGPSLocation(){
        String mapVal = mapcodeLocation.getAsMapcodeString();
        String mapGpsVal = gpsLocation.getAsMapcodeString();

        assertTrue("Mapcode should be "+mapcode+" but is "+mapGpsVal, mapVal.equalsIgnoreCase(mapGpsVal));
    }

    @Test
    public void testGetLatLonOfMapcodeLocation(){
        //Rounded because mapcode
        double latVal = mapcodeLocation.getLatitude();
        double lonVal = mapcodeLocation.getLongitude();
        double latValRounded = (double)Math.round(latVal*1000)/1000;
        double lonValRounded = (double)Math.round(lonVal*1000)/1000;
        double latRounded = (double)Math.round(latitude*1000)/1000;
        double lonRounded = (double)Math.round(longitude*1000)/1000;

        assertTrue("Latitude should be " + latitude + " but is " + latVal, latRounded == latValRounded);
        assertTrue("Longitude should be " + longitude + " but is " + lonVal, lonRounded == lonValRounded);

    }

    @Test
    public void testSetLocationOnMapcodeAsLatLon(){
        Location mLocation = new MapcodeLocation(mapcode);
        mLocation.setLocation(latTwo, lonTwo);
        String mapc = mLocation.getAsMapcodeString();
        assertTrue("Mapcode should be "+mapcodeTwo+ " but is "+mapc, mapc.equalsIgnoreCase(mapcodeTwo));
    }

    @Test
    public void testSetLocationOnMapcodeAsMapcode(){
        Location mLocation = new MapcodeLocation(mapcode);
        mLocation.setLocation(mapcodeTwo);
        String mapc = mLocation.getAsMapcodeString();
        assertTrue("Mapcode should be "+mapcodeTwo+ " but is "+mapc, mapc.equalsIgnoreCase(mapcodeTwo));
    }

    @Test
    public void testSetLocationOnGps(){
        Location mLocation = new GPSLocation(latitude, longitude);
        mLocation.setLocation(latTwo, lonTwo);
        assertTrue("lat should be "+latTwo+" but is "+mLocation.getLatitude(), mLocation.getLatitude() == latTwo);
        assertTrue("lon should be "+lonTwo+" but is "+mLocation.getLongitude(), mLocation.getLongitude() == lonTwo);

    }

    @Test
    public void testIsEqualLocation(){
        Location mGpsLoc = new GPSLocation(latitude, longitude);
        Location mAnotherGpsLoc = new GPSLocation(latitude,longitude);
        assertTrue("Should be equal but is not", mGpsLoc.isEqualLocation(mAnotherGpsLoc));
        assertTrue("Should be equal but is not", mGpsLoc.isEqualLocation(mGpsLoc));
        assertTrue("null could not be equal", !mGpsLoc.isEqualLocation(null));

        mAnotherGpsLoc.setLocation(latTwo, longitude);
        assertTrue("Should not be equal", !mGpsLoc.isEqualLocation(mAnotherGpsLoc));


    }

    @Test
    public void testAsStringMapcode(){
        String expected = mapcode;
        Location mLoc = new MapcodeLocation(latitude, longitude);
        assertEquals("AsString not as expected", expected, mLoc.asString());

    }

    @Test
    public void testAsStringGps(){
        String expected = "{lat=["+latitude+"] lon=["+longitude+"]}";
        Location mGpsLoc = new GPSLocation(latitude,longitude);
        assertEquals("AsString not as expected", expected, mGpsLoc.asString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLatitudeInConstructor(){
        Location mGpsLocation = new GPSLocation(-91, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLongitudeInConstructor(){
        Location mGpsLocation = new GPSLocation(0, 181);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMapcodeInConstructor(){
        Location mMapcodeLocation = new MapcodeLocation("ABCDEFG");
    }

    @After
    public void CleanUp(){

    }

}
