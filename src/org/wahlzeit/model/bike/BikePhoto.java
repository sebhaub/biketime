package org.wahlzeit.model.bike;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.utils.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sebi on 23.11.14.
 */
public class BikePhoto extends Photo {

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

        String susp = rset.getString(BIKE_SUSPENSION);
        String trav = rset.getString(BIKE_SUSPENSION_TRAVEL);
        
        this.suspension = SuspensionFactory.Instance().createSuspension(susp, trav);
    }

    public void writeOn(ResultSet rset) throws SQLException{
        super.writeOn(rset);
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
