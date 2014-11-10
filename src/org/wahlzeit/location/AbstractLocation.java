package org.wahlzeit.location;

import com.mapcode.Mapcode;

/**
 * Created by sebi on 07.11.14.
 */
public abstract class AbstractLocation implements Location{

    protected boolean isLocationSet = false;

    //Region abstract methods

    /***
     * does set the mapcode as the current location in all subclasses
     * assertion if the mapcode is valid has to be done before.
     * @param mapcode the mapcode to set
     * @methodtype set
     * @methodproperties primitive hook
     */
    protected abstract void doSetLocation(String mapcode);

    /***
     * does set the location to latlong in all subclasses
     * assertion if the range of the params is valid has to be done before
     * @param latitude the latitude to set in degrees
     * @param longitude the longitude to set in degrees
     * @methodproperties primitive hook
     */
    protected abstract void doSetLocation(double latitude, double longitude);

    /***
     * returns the location in a human readable format
     * @return the location as a string
     *
     * @methodtype conversion
     * @methodproperties primitive hook
     */
    protected abstract String doGetAsString();

    /***
     *
     * @return the longitude of the location
     * @methodtype query
     * @methodproperties primitive hook
     */
    protected abstract double doGetLongitude();

    /***
     *
     * @return the latitude of the location
     * @methodtype query
     * @methodproperties primitive hook
     */
    protected abstract double doGetLatitude();

    /***
     *
     * @return the location encoded as a mapcode string
     * @methodtype query
     * @methodproperties primitive hook
     */
    protected abstract String doGetMapcodeString();

    //Region implemented interface

    /***
     *
     * @return
     */
    public final String asString(){
        if(!hasLocation()){
            return "no location set";
        }
        return doGetAsString();
    }

    /***
     *
     * @return true if a location is set
     * @methodtype Boolean query
     * @methodproperties primitive
     */
    public final boolean hasLocation(){
        return this.isLocationSet;
    }

    /***
     * Does set the location to the given mapcode
     * Also does assert that the given mapcode is valid.
     * @param mapcode the mapcode string which contains the location
     * @methodtype Command
     * @methodproperties Composed
     */
    public final void setLocation(String mapcode){
        assertIsValidMapcodeString(mapcode);
        doSetLocation(mapcode);
    }

    /***
     * Sets the location to the given params.
     * Also does assert that the params are valid ones.
     * @param latitude
     * @param longitude the longitude to set
     * @methodtype Command
     * @methodproperties Composed
     */
    public final void setLocation(double latitude, double longitude){
        assertIsValidLatitude(latitude);
        assertIsValidLongitude(longitude);
        doSetLocation(latitude, longitude);
    }


    /***
     * Returns the latitude of this location
     * @return the latitude as a double
     *
     * @methodtype query
     * @methodproperties primitive
     */
    public final double getLatitude(){
        return doGetLatitude();
    }

    /***
     * Returns the longitude of this location
     * @return the longitude as a double
     *
     * @methodtype query
     * @methodproperties primitive
     */
    public final double getLongitude(){
        return doGetLongitude();
    }

    /***
     * Returns the longitude of this location
     * @return the longitude as a double
     *
     * @methodtype query
     * @methodproperties primitive
     */
    public final String getAsMapcodeString(){
       return doGetMapcodeString();
    }

    /***
     *
     * @param other the other Location
     * @return
     * @methodtype comparison
     * @methodproperties Composed
     */
    public final boolean isEqualLocation(Location other){
        if(other == null){
            return false;
        }
        if(this == other) {
            return true;
        }
        if(getLongitude() == other.getLongitude() &&
                getLatitude() == other.getLatitude()){
            return true;
        }
        return false;
    }




    //Region assertion methods

    /***
     * Checks if param is a valid latitude value
     * See <a href="http://msdn.microsoft.com/en-us/library/aa578799.aspx">MSDN</a>
     *
     * @param latitude the latitude value to check
     * @throws java.lang.IllegalArgumentException if the latitude is not a valid value
     *
     * @methodtype Assertion
     * @methodproperties primitive
     */
    protected final void assertIsValidLatitude(double latitude){
        if(!(latitude >= -90 && latitude <= 90)){
            throw new IllegalArgumentException("The value "+latitude+ " is not a valid latitude!");
        }
    }

    /***
     * Checks if param is a valid longitude value
     * See <a href="http://msdn.microsoft.com/en-us/library/aa578799.aspx">MSDN</a>
     *
     * @param longitude the latitude value to check
     * @throws java.lang.IllegalArgumentException if the longitude is not a valid value
     *
     * @methodtype Assertion
     * @methodproperties primitive
     */
    protected final void assertIsValidLongitude(double longitude){
        if (!(longitude >= -180 && longitude <= 180)){
            throw new IllegalArgumentException("The value "+longitude+ " is not a valid longitude!");
        }
    }

    /***
     * Checks if param is a valid mapcode string
     * @param mapcode the string to check
     * @throws java.lang.IllegalArgumentException if the mapcode is not a valid value
     *
     * @methodtype Assertion
     * @methodproperties primitive
     */
    protected final void assertIsValidMapcodeString(String mapcode){
         if(!Mapcode.isValidMapcodeFormat(mapcode)){
             throw new IllegalArgumentException("The value "+mapcode+ " is not a valid mapcode!");
         }
    }
}
