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

    
    public final int hashCode(){
    	int start = 1;
    	start = start * 9 + doGetAsString().hashCode();
    	start += start * 10 + doGetLatitude();
    	start += start * 11 + doGetLongitude();
    	start += start * 12 + doGetMapcodeString().hashCode();
    	return start;
    }
    
    public final boolean equals(Object other){
    	if(other == this){
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
    protected final void assertIsValidLatitude(double latitude) throws LocationException{
        if(!(latitude >= -90 && latitude <= 90)){
            throw new LocationException("The value "+latitude+ " is not a valid latitude!");
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
    protected final void assertIsValidLongitude(double longitude) throws LocationException{
        if (!(longitude >= -180 && longitude <= 180)){
            throw new LocationException("The value "+longitude+ " is not a valid longitude!");
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
    protected final void assertIsValidMapcodeString(String mapcode) throws LocationException{
         if(!Mapcode.isValidMapcodeFormat(mapcode)){
             throw new LocationException("The value "+mapcode+ " is not a valid mapcode!");
         }
    }
}
