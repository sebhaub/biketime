package org.wahlzeit.model.bike;

/**
 * Created by sebi on 23.11.14.
 */
public class FullSuspension extends BikeSuspension {

    private final int frontTravel;
    private final int rearTravel;
    
    public FullSuspension(){
    	super(SuspensionType.Full);
        this.frontTravel = 0;
        this.rearTravel = 0;
    }

    public FullSuspension(int frontTravel, int rearTravel) {
        super(SuspensionType.Full);
        assertIsValidTravel(frontTravel);
        assertIsValidTravel(rearTravel);
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
