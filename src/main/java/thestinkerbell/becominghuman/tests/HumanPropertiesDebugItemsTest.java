package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesCycleItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesDecreaseItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesIncreaseItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesOutputItem;

public class HumanPropertiesDebugItemsTest {

	Integer numberOfPropertiesToAdd = 3; //For some reason this number cannot be larger than 128
	Properties basic_properties;
	HumanPropertiesCycleItem cycle_item;
	HumanPropertiesOutputItem output_item;
	HumanPropertiesDecreaseItem decrease_item;
	HumanPropertiesIncreaseItem increase_item;
	
	
	private void createDebugItems() {
		basic_properties = new Properties();
		for(Integer i=0; i<numberOfPropertiesToAdd; ++i) {
			basic_properties.add(new BasicHumanProperty());			
		}
		
		cycle_item = new HumanPropertiesCycleItem(basic_properties);
    	output_item = new HumanPropertiesOutputItem();
    	decrease_item = new HumanPropertiesDecreaseItem();
    	increase_item = new HumanPropertiesIncreaseItem();
	}
	
	private void destroyDebugItems() {
		basic_properties.clear();
		
		cycle_item = null;
    	output_item = null;
    	decrease_item = null;
    	increase_item = null;
	}
	
	private void tryCycling() {
		Integer expected_index = output_item.getCyclingIndex();
		this.assertCyclingIndex(expected_index);
		cycle_item.cycleIndex();
		expected_index = (expected_index + 1) % basic_properties.size();
    	this.assertCyclingIndex(expected_index);
	}


	private void assertCyclingIndex(Integer expected_index) {
		assertTrue(output_item.getCyclingIndex() == expected_index);
		assertTrue(decrease_item.getCyclingIndex() == expected_index);
		assertTrue(increase_item.getCyclingIndex() == expected_index);
	}
	
	@Before
    public void setUp() {
		this.createDebugItems();
    }
	
	@After
	public void tearDown() {
		this.destroyDebugItems();
	}
	
	@Test
	public void createdItemsAreNotNull() {
		assertTrue(basic_properties.size() == numberOfPropertiesToAdd);
		assertNotNull(cycle_item);
    	assertNotNull(output_item);
    	assertNotNull(decrease_item);
    	assertNotNull(increase_item);
	}
	
	@Test
	public void canCycleItems() {
		
		assertTrue(output_item.getCyclingIndex() == -1);
		assertTrue(decrease_item.getCyclingIndex() == -1);
		assertTrue(increase_item.getCyclingIndex() == -1);
		
    	output_item.setCycleItem(cycle_item);
    	decrease_item.setCycleItem(cycle_item);
    	increase_item.setCycleItem(cycle_item);
    	    	
    	Integer numberOfPropertiesPlusOne = basic_properties.size()+1;
    	for(Integer i = 0; i <= numberOfPropertiesPlusOne; i++ )
    	{
    		this.tryCycling();
    	}
		
	}

}
