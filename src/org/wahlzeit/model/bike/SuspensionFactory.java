package org.wahlzeit.model.bike;

/***
 * This class acts as a Factory to create a Suspension instance
 * @author sebi
 *
 */
public class SuspensionFactory {
	
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
	public static Suspension createSuspension(String type, String travel){
		Suspension result = null;
		SuspensionType suspensionType = SuspensionType.valueOf(type);
		switch(suspensionType){
		case None:
			result = new SingleSuspension();
			break;
			
		case Front:
		case Rear:
			result = new SingleSuspension();
			result.setSuspensionType(type);
			result.setSuspensionTravel(Integer.parseInt(travel),suspensionType);
			break;
			
		case Full:
			result = new FullSuspension();
			String[] tokens = travel.split(delims);
			
			String front = tokens[0];
			String rear = tokens[1];
			result.setSuspensionTravel(Integer.parseInt(front), SuspensionType.Front);
			result.setSuspensionTravel(Integer.parseInt(rear), SuspensionType.Rear);
			break;
		}
		
		return result;
	}

}
