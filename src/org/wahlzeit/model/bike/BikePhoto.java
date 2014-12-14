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
 */
public class BikePhoto extends Photo {

    public static final String BIKEPART = "bikepart";
    public static final String BIKEPART_MANUFACTURER = "bikepart_manufacturer";
    public static final String BIKEPART_PRICE = "bikepart_price";

    public static final String BIKE_SUSPENSION = "suspension";
    public static final String BIKE_SUSPENSION_TRAVEL = "suspension_travel";

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

        String bikepartType = rset.getString(BIKEPART);
        String bikepartManufacturer = rset.getString(BIKEPART_MANUFACTURER);
        String bikepartPrice = rset.getString(BIKEPART_PRICE);
        String susp = rset.getString(BIKE_SUSPENSION);
        String trav = rset.getString(BIKE_SUSPENSION_TRAVEL);
        
        this.suspension = SuspensionFactory.Instance().createSuspension
        		(susp, 
        		trav, 
        		bikepartManufacturer,
        		bikepartPrice,
        		bikepartType);
    }

    public void writeOn(ResultSet rset) throws SQLException{
        super.writeOn(rset);
        
        rset.updateString(BIKEPART, suspension.getType().getPartTypeAsString());
        rset.updateString(BIKEPART_MANUFACTURER, suspension.getType().getManufacturer());
        rset.updateString(BIKEPART_PRICE, suspension.getType().getPrice());

        rset.updateString(BIKE_SUSPENSION, suspension.getSuspensionType().name());
        rset.updateString(BIKE_SUSPENSION_TRAVEL, suspension.getSuspensionTravel(suspension.getSuspensionType()));
    }
    
    public void setBikeSuspension(Suspension value){
    	this.suspension = value;
    	incWriteCount();
    }

    public Suspension getBikeSuspension(){
        return this.suspension;
    }

    public String getBikeSuspensionString(){
        return this.suspension.asString();
    }

}
