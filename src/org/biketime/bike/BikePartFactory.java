package org.biketime.bike;

import java.util.HashMap;

/***
 * This class acts as a Factory to create a Suspension instance
 * It handles and manages the creation of the value objects of BikeSuspension
 * @collaboration BikePart TypeObject
 * @role Manager
 * @collaboration BikePart Factory
 * @role Manager
 * * @author sebi
 *
 */
public class BikePartFactory {

	//Hashset which keeps track of our already created values;
	private HashMap<Integer, BikePart> createdParts;
	private static  BikePartFactory _factory;

	/***
	 * Singleton
	 * @returns the instance of this factory
	 */
	public static BikePartFactory Instance(){
		if(_factory == null){
			_factory = new BikePartFactory();
		}
		return _factory;
	}

	/***
	 * private constructor --> singleton
	 */
	private BikePartFactory(){
		this.createdParts = new HashMap<>();
	}

	/***
	 * Creates and returns a instance of a Suspension. The concrete type depends on the given params
	 * If the value object is already created and stored inside our hashmap, we will return that one,
	 * else we will add the newly created one to the hashmap for later reuse.
	 * @param type The Suspensiontype
	 * @param travel The Travel of the Suspension (combined if Full Suspension)
	 * @return
	 */
	public BikePart createBikePart(String fabricator, String price, String partType) throws BikePartInitializationException{
		BikePart result = null;
		BikePartType bikePartType = BikePartType.valueOf(partType);
		switch(bikePartType){
			case Brake:
			case Wheel:
			case Chain:
			case Tire:
			case Pedal:
			case Suspension:
			case Frame:
				try {
					result = new BikePart(fabricator, price, bikePartType);
				}catch (IllegalArgumentException e){
					throw new BikePartInitializationException(e.getMessage());
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
		synchronized (createdParts) {
			if (createdParts.containsKey(hashCode)) {
				return createdParts.get(hashCode);
			}
			createdParts.put(hashCode, result);
		}
		return result;
	}
}