package org.wahlzeit.model.bike;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

/**
 * @collaboration BikePhoto Factory
 * @role Manager
 */
public class BikePhotoFactory extends AbstractPhotoFactory{
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static BikePhotoFactory instance = null;
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized BikePhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PhotoFactory");
			setInstance(new BikePhotoFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(BikePhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}
		
		instance = photoFactory;
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	/**
	 * 
	 */
	protected BikePhotoFactory() {
		// do nothing
	}

	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {

		return new BikePhoto();
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(PhotoId id) {

		return new BikePhoto(id);
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(ResultSet rs) throws SQLException {
		return new BikePhoto(rs);
	}
	

}
