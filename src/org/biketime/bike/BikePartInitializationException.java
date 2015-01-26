package org.biketime.bike;

/**
 * Created by sebi on 18.01.15.
 */
public class BikePartInitializationException extends Exception {

    /***
     * This exception is raised when the initialization of an bikepart goes wrong
     * @param message contains the message of the inner exception which was caught
     */
    public BikePartInitializationException(String message){
        super(message);
    }
}
