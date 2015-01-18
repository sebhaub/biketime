package org.wahlzeit.model.bike;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.bike.suspension.Suspension;
import org.wahlzeit.model.bike.suspension.SuspensionFactory;
import org.wahlzeit.utils.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sebi on 23.11.14.
 * @collaboration Suspension Factory
 * @role Client
 * @collaboration BikePart TypeObject
 * @role Client
 * @collaboration BikePhoto Factory
 * @role Domain Object
 * @collaboration Serializer
 * @role Domain-object
 */
public class BikePhoto extends Photo {

    public static final String BIKEPART = "parttype";
    public static final String BIKEPART_MANUFACTURER = "manufacturer";
    public static final String BIKEPART_PRICE = "price";

    public static final String BIKE_SUSPENSION = "suspension";
    public static final String BIKE_SUSPENSION_TRAVEL = "suspension_travel";

    boolean hasSuspension = false;
    protected Suspension suspension;


    public BikePhoto(){
        super();
    }

    public BikePhoto(PhotoId id){
        super(id);
    }

    public BikePhoto(ResultSet rset) throws SQLException{
        super(rset);
    }

    public void readFrom(ResultSet rset) throws SQLException{
        super.readFrom(rset);

        String bikepartType = rset.getObject(BIKEPART) != null ? rset.getString(BIKEPART) : null;
        String bikepartManufacturer = rset.getObject(BIKEPART_MANUFACTURER) != null ? rset.getString(BIKEPART_MANUFACTURER) : null;
        String bikepartPrice = rset.getObject(BIKEPART_PRICE) != null ? rset.getString(BIKEPART_PRICE) : null;
        String susp = rset.getObject(BIKE_SUSPENSION) != null ? rset.getString(BIKE_SUSPENSION) : null;
        String trav = rset.getObject(BIKE_SUSPENSION_TRAVEL) != null ? rset.getString(BIKE_SUSPENSION_TRAVEL) : null;

        try {
            this.suspension = SuspensionFactory.Instance().createSuspension
                    (susp,
                            trav,
                            bikepartManufacturer,
                            bikepartPrice,
                            bikepartType);
            hasSuspension = true;
        }catch (BikePartInitializationException e){

        }
    }

    public void writeOn(ResultSet rset) throws SQLException{
        super.writeOn(rset);

        if(hasSuspension) {
            rset.updateString(BIKEPART, suspension.getType().getPartTypeAsString());
            rset.updateString(BIKEPART_MANUFACTURER, suspension.getType().getManufacturer());
            rset.updateString(BIKEPART_PRICE, suspension.getType().getPrice());

            rset.updateString(BIKE_SUSPENSION, suspension.getSuspensionType().name());
            rset.updateString(BIKE_SUSPENSION_TRAVEL, suspension.getSuspensionTravel(suspension.getSuspensionType()));
        }
    }
    
    public void setBikeSuspension(Suspension value){
    	this.suspension = value;
        hasSuspension = true;
    	incWriteCount();
    }

    public boolean hasSuspension(){
        return hasSuspension;
    }

    public Suspension getBikeSuspension(){
        return this.suspension;
    }

    public String getBikeSuspensionString(){
        if(!hasSuspension){
            return "No Suspension";
        }
        return this.suspension.asString();
    }

}
