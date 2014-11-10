package org.wahlzeit.location;

import com.mapcode.MapcodeCodec;
import com.mapcode.UnknownMapcodeException;

/**
 * Created by sebi on 07.11.14.
 */
public class MapcodeLocation extends AbstractLocation {

    private String mapcode;

    public MapcodeLocation(double latitude, double longitude){
        assertIsValidLatitude(latitude);
        assertIsValidLongitude(longitude);
        mapcode = MapcodeCodec.encodeToInternational(latitude,longitude).getMapcode();
        this.isLocationSet = true;
    }

    public MapcodeLocation(String mapcode){
        assertIsValidMapcodeString(mapcode);
        this.mapcode = mapcode;
        this.isLocationSet = true;
    }

    @Override
    protected void doSetLocation(String mapcode) {
        this.mapcode = mapcode;
    }

    @Override
    protected void doSetLocation(double latitude, double longitude) {
        this.mapcode = MapcodeCodec.encodeToInternational(latitude,longitude).getMapcode();
    }

    @Override
    protected String doGetAsString() {
        return mapcode;
    }

    @Override
    protected double doGetLongitude() {
        try {
            return MapcodeCodec.decode(mapcode).getLonDeg();
        } catch (UnknownMapcodeException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    protected double doGetLatitude() {
        try {
            return MapcodeCodec.decode(mapcode).getLatDeg();
        } catch (UnknownMapcodeException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    protected String doGetMapcodeString() {
        return mapcode;
    }


}
