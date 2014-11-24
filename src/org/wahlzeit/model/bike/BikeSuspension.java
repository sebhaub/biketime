package org.wahlzeit.model.bike;

/**
 * Created by sebi on 23.11.14.
 */
public abstract class BikeSuspension implements Suspension{

    protected SuspensionType mSuspensionType;

    public BikeSuspension(){
        this(SuspensionType.None);
    }

    public BikeSuspension(SuspensionType type){
        this.mSuspensionType = type;
    }

    public final boolean hasSuspension(){
        if(this.mSuspensionType != SuspensionType.None){
            return true;
        }
        return false;
    }

    public final String asString(){
        if(this.mSuspensionType == SuspensionType.None){
            return "This Bike does not have any Suspension...";
        }
        return doGetAsString();
    }

    @Override
    public SuspensionType getSuspensionType() {
        return this.mSuspensionType;
    }

    @Override
    public final void setSuspensionType(String type){
        assertIsSuspensionType(type);
        this.mSuspensionType = SuspensionType.valueOf(type);
    }

    @Override
    public void setSuspensionTravel(int value, SuspensionType type) {
        assertHasSuspensionType(type);
        doSetSuspension(type, value);
    }

    @Override
    public int getSuspensionTravel(SuspensionType type) {
        assertHasSuspensionType(type);
        return doGetSuspension(type);
    }

    protected void assertIsValidTravel(int value){
        if(value <= 0) throw new IllegalArgumentException();
    }

    protected void assertHasSuspensionType(SuspensionType type){
        if(this.mSuspensionType != type) throw new IllegalArgumentException();
    }

    protected boolean assertIsSuspensionType(String value){
        if(value == null || SuspensionType.valueOf(value) == null){
            return false;
        }
        return true;
    }

    protected abstract void doSetSuspension(SuspensionType type, int value);
    protected abstract int doGetSuspension(SuspensionType type);
    protected abstract String doGetAsString();
}
