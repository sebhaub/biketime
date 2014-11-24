package org.wahlzeit.model.bike;

/**
 * Created by sebi on 23.11.14.
 */
public class SingleSuspension extends BikeSuspension {

    private int travel = 0;

    @Override
    protected void doSetSuspension(SuspensionType type, int value) {
        this.travel = value;
    }

    @Override
    protected int doGetSuspension(SuspensionType type) {
        return this.travel;
    }

    @Override
    protected String doGetAsString() {
        return "SingleSuspension on ["+this.mSuspensionType.toString()+"] with Travel of ["+travel+"]";
    }
}
