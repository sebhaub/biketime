package org.wahlzeit.model.bike;

/**
 * Created by sebi on 23.11.14.
 */
public interface Suspension {

    public String asString();

    public boolean hasSuspension();

    public SuspensionType getSuspensionType();

    public void setSuspensionType(String type);

    public void setSuspensionTravel(int value, SuspensionType type);

    public String getSuspensionTravel(SuspensionType type);
}
