package org.biketime.bike.suspension;

import org.biketime.bike.BikePart;
import org.biketime.bike.BikePartInitializationException;

/**
 * Created by sebi on 23.11.14.
 * @collaboration {@link org.biketime.bike.BikePart} Type-Object
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
     * @returns the {@link org.biketime.bike.suspension.SuspensionType}
     */
    public SuspensionType getSuspensionType();

    /***
     * Sets the {@link org.biketime.bike.suspension.SuspensionType} to type @see java.lang.Object.Enum#valueOf(String name)
     * @param type The name of the SuspensionType to set 
     */
    public Suspension setSuspensionType(String type) throws BikePartInitializationException;

    /***
     * Sets the travel of a {@link org.biketime.bike.suspension.SuspensionType}
     * @param value the travel in mm
     * @param type the {@link org.biketime.bike.suspension.SuspensionType}
     */
    public Suspension setSuspensionTravel(int value, SuspensionType type) throws BikePartInitializationException;

    /***
     * Returns the SuspensionTravel for a given type
     * @param type the {@link org.biketime.bike.suspension.SuspensionType}
     * @return
     */
    public String getSuspensionTravel(SuspensionType type);


    public boolean equals(Object obj);

    public int hashCode();
}
