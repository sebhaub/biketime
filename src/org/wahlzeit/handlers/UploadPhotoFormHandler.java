/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.handlers;

import java.util.*;
import java.io.*;

import org.biketime.location.Location;
import org.biketime.location.LocationException;
import org.biketime.location.LocationFactory;
import org.wahlzeit.model.*;
import org.biketime.bike.BikePhoto;
import org.biketime.bike.suspension.Suspension;
import org.biketime.bike.suspension.SuspensionFactory;
import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;
import org.wahlzeit.webparts.*;

/**
 * @author dirkriehle
 */
public class UploadPhotoFormHandler extends AbstractWebFormHandler {

    /**
     *
     */
    public UploadPhotoFormHandler() {
        initialize(PartUtil.UPLOAD_PHOTO_FORM_FILE, AccessRights.USER);
    }

    /**
     *
     */
    protected void doMakeWebPart(UserSession us, WebPart part) {
        Map<String, Object> args = us.getSavedArgs();
        part.addStringFromArgs(args, UserSession.MESSAGE);

        part.maskAndAddStringFromArgs(args, Photo.TAGS);
        part.maskAndAddStringFromArgs(args, Photo.LATITUDE);
        part.maskAndAddStringFromArgs(args, Photo.LONGITUDE);

    }

    /**
     *
     */
    protected String doHandlePost(UserSession us, Map args) {
        String tags = us.getAndSaveAsString(args, Photo.TAGS);

        //our location values
        String latitude = us.getAndSaveAsString(args, Photo.LATITUDE);
        String longitude = us.getAndSaveAsString(args, Photo.LONGITUDE);

        Location mPhotoLocation = null;
        //check if the values are doubles
        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);

            mPhotoLocation = LocationFactory.getInstance().createGPSLocation(lat, lon);

        } catch (NumberFormatException nformatException) {

        } catch (NullPointerException nullException) {

        } catch (LocationException locationException) {
            //Photolocation is null
        }

        String bikepartType = us.getAndSaveAsString(args, BikePhoto.BIKEPART);
        String manufacturer = us.getAndSaveAsString(args, BikePhoto.BIKEPART_MANUFACTURER);
        String price = us.getAndSaveAsString(args, BikePhoto.BIKEPART_PRICE);

        String suspension = us.getAndSaveAsString(args, BikePhoto.BIKE_SUSPENSION);
        String suspensionTravel = us.getAndSaveAsString(args, BikePhoto.BIKE_SUSPENSION_TRAVEL);


        if (!StringUtil.isLegalTagsString(tags)) {
            us.setMessage(us.cfg().getInputIsInvalid());
            return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
        }

        try {
            PhotoManager pm = PhotoManager.getInstance();
            String sourceFileName = us.getAsString(args, "fileName");
            File file = new File(sourceFileName);
            Photo photo = pm.createPhoto(file);

            if (photo instanceof BikePhoto && suspension != null && suspensionTravel != null) {

                Suspension bikeSuspension = null;
                if (bikepartType.equalsIgnoreCase("")
                        || manufacturer.equalsIgnoreCase("")
                        || price.equalsIgnoreCase("")) {
                    bikeSuspension = SuspensionFactory.Instance().
                            createSuspension(suspension, suspensionTravel);
                } else {
                    bikeSuspension = SuspensionFactory.Instance().
                            createSuspension(suspension, suspensionTravel, manufacturer, price, bikepartType);
                }
                ((BikePhoto) photo).setBikeSuspension(bikeSuspension);
            }

            String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
            createBackup(sourceFileName, targetFileName);

            User user = (User) us.getClient();
            user.addPhoto(photo);

            photo.setTags(new Tags(tags));
            if(mPhotoLocation != null){
                photo.setPhotoLocation(mPhotoLocation);
            }

            pm.savePhoto(photo);

            StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
            UserLog.addCreatedObject(sb, "Photo", photo.getId().asString());
            UserLog.log(sb);
            if(mPhotoLocation == null){
                us.setThreeLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getPhotoUploadLocationError(), us.cfg().getKeepGoing());
            }else{
                us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
            }
        } catch (Exception ex) {
            SysLog.logThrowable(ex);
            us.setMessage(us.cfg().getPhotoUploadFailed());
        }

        return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
    }

    /**
     *
     */
    protected void createBackup(String sourceName, String targetName) {
        try {
            File sourceFile = new File(sourceName);
            InputStream inputStream = new FileInputStream(sourceFile);
            File targetFile = new File(targetName);
            OutputStream outputStream = new FileOutputStream(targetFile);
            // @FIXME IO.copy(inputStream, outputStream);
        } catch (Exception ex) {
            SysLog.logSysInfo("could not create backup file of photo");
            SysLog.logThrowable(ex);
        }
    }
}
