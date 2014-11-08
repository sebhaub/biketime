package org.wahlzeit.location;

/**
 * Created by sebi on 07.11.14.
 */
public interface Location {

    /***
     * Human readable respresentation
     * @return a human readable String of the location
     */
    public String asString();

    /***
     *
     * @return true if this instance has a valid location set
     */
    public boolean hasLocation();

    /***
     * sets the location to the given mapcode
     * does a conversion to latlon if needed
     * @param mapcode the mapcode string which contains the location
     */
    public void setLocation(String mapcode);

    /***
     * sets the location to the given latlon
     * does a conversion to mapcode if needed
     * @param latidute the latitude to set
     * @param longitude the longitude to set
     */
    public void setLocation(double latidute, double longitude);

    /***
     * Checks if two Locations are the same
     * @param other the other Location
     * @return true if it is the same location, false if other is null or otherwise
     */
    public boolean isEqualLocation(Location other);

    /***
     *
     * @return the latitude in degrees
     */
    public double getLatitude();

    /***
     *
     * @return the longitude in degrees
     */
    public double getLongitude();

    /***
     *
     * @return The current location encoded as a mapcode string
     */
    public String getAsMapcodeString();
}
