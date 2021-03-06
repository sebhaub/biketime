package org.biketime.bike.suspension;

import org.biketime.bike.BikePart;
import org.biketime.bike.BikePartInitializationException;

/**
 * Created by sebi on 23.11.14.
 */
public class FullSuspension extends BikeSuspension {

    private final int frontTravel;
    private final int rearTravel;
    
    protected FullSuspension(BikePart partType){
    	super(partType, SuspensionType.Full);
        this.frontTravel = 0;
        this.rearTravel = 0;
    }

    protected FullSuspension(BikePart partType, int frontTravel, int rearTravel) throws BikePartInitializationException{
        super(partType, SuspensionType.Full);
        try {
            assertIsValidTravel(frontTravel);
            assertIsValidTravel(rearTravel);
        }catch (IllegalArgumentException e){
            throw new BikePartInitializationException(e.getMessage());
        }
        this.frontTravel = frontTravel;
        this.rearTravel = rearTravel;
    }


    @Override
    protected String doGetSuspension(SuspensionType type) {
        switch (type){
            case Front:
                return this.frontTravel+"";
            case Rear:
                return this.rearTravel+"";
            default:
            	return this.frontTravel+"|"+this.rearTravel;
        }
    }

    @Override
    protected String doGetAsString() {
        return "FullSuspension with RearTravel of ["+this.rearTravel+"] and FrontTravel of ["+this.frontTravel+"]";
    }
}
