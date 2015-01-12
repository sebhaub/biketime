package org.wahlzeit.location;

/**
 * Created by sebi on 07.11.14.
 * @collaboration photo / Location
 * @role domain object
 * @role
 */
public interface Location {

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
     * Human readable respresentation
     * @return a human readable String of the location
     * @collaboration photo caption
     */
    public String asString();

    /***
     *
     * @return The current location encoded as a mapcode string
     * @collaboration photo caption
     */
    public String getAsMapcodeString();
    
    public int hashCode();
    
    public boolean equals(Object other);
}
