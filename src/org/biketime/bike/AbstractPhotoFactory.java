package org.biketime.bike;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFilter;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoTagCollector;

public abstract class AbstractPhotoFactory {
	
	/**
	 * 
	 */
	public PhotoFilter createPhotoFilter() {
		return new PhotoFilter();
	}
	
	/**
	 * 
	 */
	public PhotoTagCollector createPhotoTagCollector() {
		return new PhotoTagCollector();
	}
	
	public abstract Photo createPhoto();
	/**
	 * 
	 */
	public abstract Photo createPhoto(PhotoId id);
	
	/**
	 * 
	 */
	public abstract Photo createPhoto(ResultSet rs) throws SQLException;
	
}
