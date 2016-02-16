package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.basic.WhiteBloodCellsBasicHumanProperty;

public class WhiteBloodCellsTest {

	@Test
	public void canCreateWhiteBloodCellProperty() {
		WhiteBloodCellsBasicHumanProperty white_blood_cells = new WhiteBloodCellsBasicHumanProperty();
		assertNotNull(white_blood_cells);
	}

}
