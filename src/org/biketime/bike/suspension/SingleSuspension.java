package org.biketime.bike.suspension;

import org.biketime.bike.BikePart;
import org.biketime.bike.BikePartInitializationException;

/**
 * Created by sebi on 23.11.14.
 */
public class SingleSuspension extends BikeSuspension {

    private final int travel;

    protected SingleSuspension(BikePart partType, SuspensionType type){
        super(partType, type);
        this.travel = 0;
    }

    protected SingleSuspension(BikePart partType, SuspensionType type, int travel) throws BikePartInitializationException{
        super(partType, type);
        try {
            assertIsValidTravel(travel);
        }catch (IllegalArgumentException e){
            throw new BikePartInitializationException(e.getMessage());
        }
        this.travel = travel;
    }

    @Override
    protected String doGetSuspension(SuspensionType type) {
        return this.travel+"";
    }

    @Override
    protected String doGetAsString() {
        return "SingleSuspension on ["+this.mSuspensionType.toString()+"] with Travel of ["+travel+"]";
    }
}
