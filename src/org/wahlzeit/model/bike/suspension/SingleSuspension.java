package org.wahlzeit.model.bike.suspension;

/**
 * Created by sebi on 23.11.14.
 */
public class SingleSuspension extends BikeSuspension {

    private final int travel;

    protected SingleSuspension(SuspensionType type){
        super(type);
        this.travel = 0;
    }

    protected SingleSuspension(SuspensionType type, int travel){
        super(type);
        assertIsValidTravel(travel);
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
