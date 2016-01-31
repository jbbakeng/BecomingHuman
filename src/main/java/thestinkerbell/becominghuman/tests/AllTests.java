package thestinkerbell.becominghuman.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	HumanPropertiesTest.class, 
	HumanPropertyTest.class,
	HumanPropertiesDebugItems.class
	})
public class AllTests {

}
