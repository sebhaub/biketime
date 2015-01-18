package org.wahlzeit.model.bike.suspension;

import org.wahlzeit.model.bike.BikePart;
import org.wahlzeit.model.bike.BikePartInitializationException;

/**
 * Created by sebi on 23.11.14.
 * @collaboration BikePart Type-Object
 * @role BaseObject
 */
public interface Suspension {
	
	/***
	 * 
	 * @return the type-object of this implementation
	 */
	public BikePart getType();

	/***
	 * 
	 * @return a human readable String of this implementations suspension
	 */
    public String asString();

    /***
     * 
     * @return true if this implementations have a suspension
     */
    public boolean hasSuspension();

    /***
     * 
     * @return the SuspensionType
     */
    public SuspensionType getSuspensionType();

    /***
     * Sets the suspensiontype to type (see Enums.valueOf(String param)
     * @param type The name of the SuspensionType to set 
     */
    public Suspension setSuspensionType(String type) throws BikePartInitializationException;

    /***
     * Sets the travel of a suspensiontype
     * @param value the travel in mm
     * @param type the SuspensionType 
     */
    public Suspension setSuspensionTravel(int value, SuspensionType type) throws BikePartInitializationException;

    /***
     * Returns the SuspensionTravel for a given type
     * @param type the Suspensiontype
     * @return
     */
    public String getSuspensionTravel(SuspensionType type);


    public boolean equals(Object obj);

    public int hashCode();
}
