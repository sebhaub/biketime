package org.wahlzeit.model.bike.suspension;

import org.wahlzeit.model.bike.BikePart;

/***
 * The abstract Suspension class
 * @invariant if no values are set, SuspensionType is None, and asString is callable
 * value-object
 * @author sebi
 *
 */
public abstract class BikeSuspension implements Suspension{

	protected BikePart mType;
    protected final SuspensionType mSuspensionType;

    /***
     * Protected constructor
     * The subclasses have to call this one to initialize the final variable mSuspensiontype
     * @param type the SuspensionType
     */
    protected BikeSuspension(BikePart partType, SuspensionType suspType){
        this.mSuspensionType = suspType;
        this.mType = partType;
    }

    @Override
    public final boolean hasSuspension(){
        if(this.mSuspensionType != SuspensionType.None){
            return true;
        }
        return false;
    }

    @Override
    public final String asString(){
        if(this.mSuspensionType == SuspensionType.None){
            return "This Bike does not have any Suspension...";
        }
        return doGetAsString();
    }

    @Override
    public final SuspensionType getSuspensionType() {
        return this.mSuspensionType;
    }


    /***
     * Since we have a value-object this method will not "set" a value on this instance
     * but instead return a "new" instance with this suspensions travel and the setted type
     * using the SuspensionFactory
     * @param type The name of the SuspensionType to set
     * @return a BikeSuspension Instance with the setted value
     */
    @Override
    public final Suspension setSuspensionType(String type){
        assertIsSuspensionType(type);
        return SuspensionFactory.Instance().createSuspension(type, this.getSuspensionTravel(SuspensionType.valueOf(type)));
    }

    /***
     * Since we have a value-object this method will not "set" a value on this instance
     * but instead return a "new" instance with this suspensions type and the setted travel
     * using the SuspensionFactory
     * @param type The name of the SuspensionType to set
     * @return
     */
    @Override
    public final Suspension setSuspensionTravel(int value, SuspensionType type) {
        assertHasSuspensionType(type);
        return SuspensionFactory.Instance().createSuspension(type.name(), value+"");
    }

    @Override
    public final String getSuspensionTravel(SuspensionType type) {
        assertHasSuspensionType(type);
        return doGetSuspension(type);
    }

    /***
     * Overriding objects equals method
     *
     * @param obj the object to check for equality
     * @return true if the objects are equal, false otherwise
     */
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

    /***
     * OVerriding objects hashcode method
     * @return a hashcode built from the hashcodes of the class members
     */
    @Override
    public final int hashCode() {
        int hash = 25;
        hash = hash * 11 + this.getSuspensionType().hashCode();
        hash = hash * 20 + this.getSuspensionTravel(this.getSuspensionType()).hashCode();
        hash = hash * 14 + this.asString().hashCode();
        return hash;
    }
    protected final void assertIsValidTravel(int value){
        if(value <= 0) throw new IllegalArgumentException();
    }

    protected final void assertHasSuspensionType(SuspensionType type){
    	if(this.mSuspensionType == SuspensionType.Full) return;
        if(this.mSuspensionType != type) throw new IllegalArgumentException();
    }

    protected final boolean assertIsSuspensionType(String value){
        if(value == null || SuspensionType.valueOf(value) == null){
            return false;
        }
        return true;
    }

    protected abstract String doGetSuspension(SuspensionType type);
    protected abstract String doGetAsString();
}
