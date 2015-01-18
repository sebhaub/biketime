package org.wahlzeit.model.bike;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.bike.suspension.Suspension;
import org.wahlzeit.model.bike.suspension.SuspensionFactory;
import org.wahlzeit.model.bike.suspension.SuspensionType;

public class BikePartTest{
	
	String manufacturerFox = "FOX";
	String manufacturerRockShox = "RockShox";
	
	String partTypeSuspension = "Suspension";
	String partTypeFrame = "Frame";
	
	String priceA = "999.00";
	String priceB = "1333.00";

    @Before
    public void setUp() {
    		
    }
    
    @Test
    public void testTypeObjectEqualDifferentType() throws BikePartInitializationException{
    	BikePart partA = BikePartFactory.Instance().createBikePart(manufacturerFox, priceA, partTypeFrame);
    	BikePart partB = BikePartFactory.Instance().createBikePart(manufacturerFox, priceA, partTypeFrame);
    	
    	assertTrue("Type-Objects are not equal!", partA.equals(partB));
    }
    
    @Test
    public void testTypeObjectNotEqualDifferentType() throws BikePartInitializationException{
    	BikePart partA = BikePartFactory.Instance().createBikePart(manufacturerFox, priceA, partTypeFrame);
    	BikePart partB = BikePartFactory.Instance().createBikePart(manufacturerRockShox, priceA, partTypeFrame);
    	
    	assertTrue("Type-Objects are not equal!", !partA.equals(partB));
    }
    
    @Test
    public void testGetValues() throws BikePartInitializationException{
    	BikePart partA = BikePartFactory.Instance().createBikePart(manufacturerFox, priceA, partTypeFrame);
    	
    	assertTrue("Manufacturer wrong!", partA.getManufacturer().equals(manufacturerFox));
    	assertTrue("Price is wrong!", partA.getPrice().equals(priceA));
    	assertTrue("Part type wrong!", partA.getPartTypeAsString().equals(partTypeFrame));

    }
    
    @Test
    public void testTwoSuspensionsEqual() throws BikePartInitializationException{
    	Suspension foxSuspensionFull = SuspensionFactory.Instance().
    			createSuspension("Full", "180|120", manufacturerFox, priceA, partTypeSuspension);
    	Suspension foxSuspensionFront = SuspensionFactory.Instance().
    			createSuspension("Front", "80", manufacturerFox, priceA, partTypeSuspension);
    	
    	assertTrue("Type-Objects are not equal!", foxSuspensionFull.getType().equals(foxSuspensionFront.getType()));
    }
    
    @Test
    public void testTwoSuspensionsNotEqual() throws BikePartInitializationException{
    	Suspension foxSuspensionFull = SuspensionFactory.Instance().
    			createSuspension("Full", "180|120", manufacturerFox, priceA, partTypeSuspension);
    	Suspension foxSuspensionFront = SuspensionFactory.Instance().
    			createSuspension("Front", "80", manufacturerFox, priceB, partTypeSuspension);
    	
    	assertTrue("Type-Objects are equal!", !foxSuspensionFull.getType().equals(foxSuspensionFront.getType()));
    }

	@Test
    public void testTypeObjectEqualSuspensionType() throws BikePartInitializationException{
    	BikePart partA = BikePartFactory.Instance().createBikePart(manufacturerFox, priceA, partTypeSuspension);
    	Suspension foxSuspension = SuspensionFactory.Instance().
    			createSuspension("Full", "180|120", manufacturerFox, priceA, partTypeSuspension);
    	
    	assertTrue("Type-Objects are not equal!", partA.equals(foxSuspension.getType()));
    }

	@Test
    public void testTypeObjectNotEqualSuspensionType() throws BikePartInitializationException{
    	BikePart partA = BikePartFactory.Instance().createBikePart(manufacturerFox, priceA, partTypeSuspension);
    	Suspension foxSuspension = SuspensionFactory.Instance().
    			createSuspension("Full", "180|120", manufacturerRockShox, priceA, partTypeSuspension);
    	assertTrue("Type-Objects are equal!", !partA.equals(foxSuspension.getType()));
    }

	@Test(expected = BikePartInitializationException.class)
	public void testBikePartInitializationException() throws BikePartInitializationException{
		BikePart partA = BikePartFactory.Instance().createBikePart(manufacturerFox, "-10", partTypeSuspension);
	}

	@Test(expected = BikePartInitializationException.class)
	public void testSuspensionBikePartInitializationException() throws BikePartInitializationException{
		Suspension suspA = SuspensionFactory.Instance().createSuspension(SuspensionType.Front.name(), "-10");
	}
}
