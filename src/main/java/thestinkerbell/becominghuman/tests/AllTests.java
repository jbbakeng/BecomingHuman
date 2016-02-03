package thestinkerbell.becominghuman.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	HumanTest.class, 
	HumanPropertyTest.class,
	HumanPropertiesDebugItemsTest.class,
	RiskTest.class
	})
public class AllTests {

}
