package org.wahlzeit.model.bike;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sebi on 08.11.14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BikeDomainTest.class, BikePartTest.class})
public class AllTests {


    public static Test suite() {
        TestSuite suite = new TestSuite();
        return suite;
    }
}
