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
    
    public int hashCode();
    
    public boolean equals(Object other);
}
