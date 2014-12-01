package org.wahlzeit.model.bike;

/***
 * The abstract Suspension class
 * @invariant if no values are set, SuspensionType is None, and asString is callable
 * value-object
 * @author sebi
 *
 */
public abstract class BikeSuspension implements Suspension{

    protected final SuspensionType mSuspensionType;

    protected BikeSuspension(SuspensionType type){
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
    public final Suspension setSuspensionType(String type){
        assertIsSuspensionType(type);
        return SuspensionFactory.Instance().createSuspension(type, this.getSuspensionTravel(SuspensionType.valueOf(type)));
    }

    @Override
    public Suspension setSuspensionTravel(int value, SuspensionType type) {
        assertHasSuspensionType(type);
        return SuspensionFactory.Instance().createSuspension(type.name(), value+"");
    }

    @Override
    public String getSuspensionTravel(SuspensionType type) {
        assertHasSuspensionType(type);
        return doGetSuspension(type);
    }

    @Override
    public final boolean equals(Object obj){
        if(obj == null) {
            return false;
        }
        if(!(obj.getClass().isInstance(this.getClass()))){
            return false;
        }
        BikeSuspension susp = (BikeSuspension)obj;
        if(susp.getSuspensionType() != this.getSuspensionType()) {
            return false;
        }
        if(susp.getSuspensionTravel(susp.getSuspensionType()) != this.getSuspensionTravel(this.getSuspensionType())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 25;
        hash = hash * 11 + this.getSuspensionType().hashCode();
        hash = hash * 20 + this.getSuspensionTravel(this.getSuspensionType()).hashCode();
        hash = hash * 14 + this.asString().hashCode();
        return hash;
    }
    protected void assertIsValidTravel(int value){
        if(value <= 0) throw new IllegalArgumentException();
    }

    protected void assertHasSuspensionType(SuspensionType type){
    	if(this.mSuspensionType == SuspensionType.Full) return;
        if(this.mSuspensionType != type) throw new IllegalArgumentException();
    }

    protected boolean assertIsSuspensionType(String value){
        if(value == null || SuspensionType.valueOf(value) == null){
            return false;
        }
        return true;
    }

    protected abstract String doGetSuspension(SuspensionType type);
    protected abstract String doGetAsString();
}
