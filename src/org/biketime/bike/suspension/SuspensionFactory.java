package org.biketime.bike.suspension;

import java.util.HashMap;

import org.biketime.bike.BikePart;
import org.biketime.bike.BikePartFactory;
import org.biketime.bike.BikePartInitializationException;

/***
 * This class acts as a Factory to create a Suspension instance
 * It handles and manages the creation of the value objects of BikeSuspension
 * @collaboration {@link org.biketime.bike.suspension.Suspension} Factory
 * @role Manager
 * @collaboration {@link org.biketime.bike.BikePart} Factory
 * @role Client
 * * @author sebi
 *
 */
public class SuspensionFactory {

	//Hashset which keeps track of our already created values;
	private HashMap<Integer, Suspension> availableSuspensions;
	private static  SuspensionFactory _factory;

	/***
	 * Singleton
	 * @returns the instance of this factory
	 */
	public static SuspensionFactory Instance(){
		if(_factory == null){
			_factory = new SuspensionFactory();
		}
		return _factory;
	}

	/***
	 * private constructor --> singleton
	 */
	private SuspensionFactory(){
		this.availableSuspensions = new HashMap<>();
	}


	/***
	 * The delimiter used to Split the travel of fullsuspension
	 */
	private static String delims = "[|]";

	/***
	 * Creates and returns a instance of a {@link org.biketime.bike.suspension.Suspension}. The concrete type depends on the given params
	 * If the value object is already created and stored inside our hashmap, we will return that one,
	 * else we will add the newly created one to the hashmap for later reuse.
	 * @param type The {@link org.biketime.bike.suspension.SuspensionType}
	 * @param travel The Travel of the {@link org.biketime.bike.suspension.Suspension} (combined if Full Suspension)
	 * @return
	 */
	public Suspension createSuspension(String type, String travel) throws BikePartInitializationException{
		return createSuspension(type, travel, "Unknown", "0.0", "Suspension");
	}

	public Suspension createSuspension(String type, String travel, String manufacturer, String price, String partType) throws BikePartInitializationException{
		Suspension result = null;
		SuspensionType suspensionType;
		try {
			suspensionType = SuspensionType.valueOf(type);
		}catch (IllegalArgumentException e){
			throw new BikePartInitializationException("No valid Suspensiontype");
		}
		BikePart bikePartType = BikePartFactory.Instance().createBikePart(manufacturer, price, partType);
		switch(suspensionType){
		case None:
			result = new SingleSuspension(bikePartType,SuspensionType.None);
			break;
			
		case Front:
		case Rear:
			result = new SingleSuspension(bikePartType,suspensionType, Integer.parseInt(travel));
			break;
			
		case Full:
			String[] tokens = travel.split(delims);
			int frontTravel = 0;
			int rearTravel = 0;
			try{
				String front = tokens[0];
				String rear = tokens[1];
				frontTravel = Integer.parseInt(front);
				rearTravel = Integer.parseInt(rear);
			}catch(Exception e){
				throw new BikePartInitializationException("Can´t get travel of suspension");
			}

			if(frontTravel == 0 || rearTravel == 0){
				result = new FullSuspension(bikePartType);
			}else{
				result = new FullSuspension(bikePartType,frontTravel, rearTravel);
			}
			break;
			default:
				throw new BikePartInitializationException("No valid Suspensiontype");
		}

		//if our hashmap already has this value, we dont neet to add it again
		//So just return our existing one
		//else add the new one
		//@synchronized to keep it consistent in a multithreaded usage
		int hashCode = result.hashCode();
		synchronized (availableSuspensions) {
			if (availableSuspensions.containsKey(hashCode)) {
				return availableSuspensions.get(hashCode);
			}
			availableSuspensions.put(hashCode, result);
		}
		return result;
	}
}
