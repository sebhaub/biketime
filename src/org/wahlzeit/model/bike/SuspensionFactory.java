package org.wahlzeit.model.bike;

import java.util.HashMap;
import java.util.HashSet;

/***
 * This class acts as a Factory to create a Suspension instance
 * @author sebi
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
	 * private constructor
	 */
	private SuspensionFactory(){
		this.availableSuspensions = new HashMap<>();
	}


	/***
	 * The delimiter used to Split the travel of fullsuspension
	 */
	private static String delims = "[|]";

	/***
	 * Creates and returns a new instance of a Suspension. The concrete type depends on the given params
	 * @param type The Suspensiontype
	 * @param travel The Travel of the Suspension (combined if Full Suspension)
	 * @return
	 */
	public Suspension createSuspension(String type, String travel){
		Suspension result = null;
		SuspensionType suspensionType = SuspensionType.valueOf(type);
		switch(suspensionType){
		case None:
			result = new SingleSuspension(SuspensionType.None);
			break;
			
		case Front:
		case Rear:
			result = new SingleSuspension(suspensionType, Integer.parseInt(travel));
			break;
			
		case Full:
			String[] tokens = travel.split(delims);
			
			String front = tokens[0];
			String rear = tokens[1];
			int frontTravel = Integer.parseInt(front);
			int rearTravel = Integer.parseInt(rear);
			if(frontTravel == 0 || rearTravel == 0){
				result = new FullSuspension();
			}else{
				result = new FullSuspension(frontTravel, rearTravel);
			}
			break;
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
