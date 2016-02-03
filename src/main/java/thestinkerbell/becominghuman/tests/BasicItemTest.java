package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import net.minecraft.creativetab.CreativeTabs;
import thestinkerbell.becominghuman.items.BasicItem;

public class BasicItemTest {

	@Test
	public void canCreateADefaultBasicItem() {
		BasicItem item = new BasicItem();
	}
	
	@Test
	public void canCreateABasicItemWithAUnlocalizedName() {
		BasicItem item = new BasicItem("UnlocalizedName");
		assertTrue(item.getCreativeTab() == CreativeTabs.tabMaterials);
	}

}
