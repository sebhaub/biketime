package org.wahlzeit.model.bike;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.location.GPSLocation;
import org.wahlzeit.location.MapcodeLocation;
import org.wahlzeit.model.bike.suspension.Suspension;
import org.wahlzeit.model.bike.suspension.SuspensionFactory;
import org.wahlzeit.model.bike.suspension.SuspensionType;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class BikeDomainTest{

    @Before
    public void setUp() {

    }
    
    @Test
    public void testFactoryFull() throws BikePartInitializationException{
    	Suspension fullSuspension = SuspensionFactory.Instance().createSuspension("Full", "120|100");
    	assertTrue("Travel not correct", fullSuspension.getSuspensionTravel(SuspensionType.Front).equalsIgnoreCase(120+""));
    	assertTrue("Travel not correct", fullSuspension.getSuspensionTravel(SuspensionType.Rear).equalsIgnoreCase(100+""));
    	assertTrue("Travel not correct", fullSuspension.getSuspensionTravel(SuspensionType.Full).equalsIgnoreCase("120|100"));

    	
    }
    
    @Test
    public void testFactoryFront() throws BikePartInitializationException{
    	Suspension front = SuspensionFactory.Instance().createSuspension("Front", "120");
    	assertTrue("Travel not correct", front.getSuspensionTravel(SuspensionType.Front).equalsIgnoreCase(120+""));
    }

    @Test
    public void testFactoryRear() throws BikePartInitializationException{
    	Suspension front = SuspensionFactory.Instance().createSuspension("Rear", "100");
    	assertTrue("Travel not correct", front.getSuspensionTravel(SuspensionType.Rear).equalsIgnoreCase(100+""));
    }
    
    @Test
    public void testFactoryNone() throws BikePartInitializationException{
    	Suspension front = SuspensionFactory.Instance().createSuspension("None", "100");
    	assertTrue("Travel not correct", front.hasSuspension() == false);
    }
    
    @Test(expected = BikePartInitializationException.class)
    public void testFactoryFullException() throws BikePartInitializationException{
    	Suspension front = SuspensionFactory.Instance().createSuspension("Full", "120|||");
    }
    
    @Test(expected = BikePartInitializationException.class)
    public void testFactoryWrongType() throws BikePartInitializationException{
    	Suspension front = SuspensionFactory.Instance().createSuspension("bla", "120");
    }
    
    
    @After
    public void CleanUp(){

    }

}
