package org.wahlzeit.model.bike;

public class SuspensionFactory {
	
	private static String delims = "[|]";

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
