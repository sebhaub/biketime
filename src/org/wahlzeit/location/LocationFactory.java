package org.wahlzeit.location;

import java.util.HashMap;

/**
 * @collaboration photo / Location
 * @role Manager
 */
public final class LocationFactory {
	
	private static LocationFactory instance = null;
	private static HashMap<Integer, Location> createdLocations;
	
	protected LocationFactory(){
		createdLocations = new HashMap<>();
	}
	
	public static synchronized LocationFactory getInstance(){
		if(instance == null){
			instance = new LocationFactory();
		}
		return instance;
	}
	
	
	public Location createGPSLocation(String mapcode) throws LocationException{
		Location loc = new GPSLocation(mapcode);
		return ensureLocation(loc);
	}
	
	public Location createGPSLocation(double latitude, double longitude) throws LocationException{
		Location loc = new GPSLocation(latitude, longitude);
		return ensureLocation(loc);
	}
	
	public Location createMapCodeLocation(String mapcode) throws LocationException{
		Location loc = new MapcodeLocation(mapcode);
		return ensureLocation(loc);
	}
	
	public Location createMapCodeLocation(double latitude, double longitude) throws LocationException{
		Location loc = new MapcodeLocation(latitude, longitude);
		return ensureLocation(loc);
	}
	
	private synchronized Location ensureLocation(Location loc){
		int hash = loc.hashCode();
		if(createdLocations.containsKey(hash)){
			return createdLocations.get(hash);
		}
		createdLocations.put(hash, loc);
		return loc;
	}
	
}
