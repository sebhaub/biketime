package org.wahlzeit.model.bike;

/**
 * Created by sebi on 23.11.14.
 */
public class FullSuspension extends BikeSuspension {

    private int frontTravel = 0;
    private int rearTravel = 0;

    @Override
    protected void doSetSuspension(SuspensionType type, int value) {
        switch (type){
            case Front:
                this.frontTravel = value;
                break;
            case Rear:
                this.rearTravel = value;
                break;
        }
    }

    @Override
    protected int doGetSuspension(SuspensionType type) {
        switch (type){
            case Front:
                return this.frontTravel;
            case Rear:
                return this.rearTravel;
            default:
                //should never be reached
                return 0;
        }
    }

    @Override
    protected String doGetAsString() {
        return "FullSuspension with RearTravel of ["+this.rearTravel+"] and FrontTravel of ["+this.frontTravel+"]";
    }
}
