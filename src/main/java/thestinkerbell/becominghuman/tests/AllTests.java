package thestinkerbell.becominghuman.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	HumanTest.class, 
	HumanPropertyTest.class,
	BloodPressureTest.class,
	HumanPropertiesDebugItemsTest.class,
	RiskTest.class,
	BasicItemTest.class,
	PairTest.class
	})
public class AllTests {

}
