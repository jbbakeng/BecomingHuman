package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import thestinkerbell.becominghuman.human.HumanProperty;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesCycleItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesDecreaseItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesIncreaseItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesOutputItem;

public class HumanPropertiesDebugItems {

	Integer numberOfPropertiesToAdd = 3; //For some reason this number cannot be larger than 128
	List<HumanProperty> properties;
	HumanPropertiesCycleItem cycle_item;
	HumanPropertiesOutputItem output_item;
	HumanPropertiesDecreaseItem decrease_item;
	HumanPropertiesIncreaseItem increase_item;
	
	
	private void createDebugItems() {
		properties = new ArrayList();
		for(Integer i=0; i<numberOfPropertiesToAdd; ++i) {
			properties.add(new HumanProperty());			
		}
		
		cycle_item = new HumanPropertiesCycleItem(properties);
    	output_item = new HumanPropertiesOutputItem();
    	decrease_item = new HumanPropertiesDecreaseItem();
    	increase_item = new HumanPropertiesIncreaseItem();
	}
	
	private void destroyDebugItems() {
		properties.clear();
		
		cycle_item = null;
    	output_item = null;
    	decrease_item = null;
    	increase_item = null;
	}
	
	private void tryCycling() {
		Integer expected_index = output_item.getCyclingIndex();
		this.assertCyclingIndex(expected_index);
		cycle_item.cycleIndex();
		expected_index = (expected_index + 1) % properties.size();
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
		assertTrue(properties.size() == numberOfPropertiesToAdd);
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
    	    	
    	Integer numberOfPropertiesPlusOne = properties.size()+1;
    	for(Integer i = 0; i <= numberOfPropertiesPlusOne; i++ )
    	{
    		this.tryCycling();
    	}
		
	}

}
