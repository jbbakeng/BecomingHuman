package thestinkerbell.becominghuman.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesCycleItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesDecreaseItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesIncreaseItem;
import thestinkerbell.becominghuman.items.debug.HumanPropertiesOutputItem;

public final class ModItems {
	
	public static Item tutorialItem;
	
    public static void createItems() {
    	GameRegistry.registerItem(tutorialItem = new BasicItem("tutorial_item"), "tutorial_item");

    	registerDebugItems();
    }

	private static void registerDebugItems() {
		HumanPropertiesCycleItem cycle_item = new HumanPropertiesCycleItem();
    	HumanPropertiesOutputItem output_item = new HumanPropertiesOutputItem();
    	HumanPropertiesDecreaseItem decrease_item = new HumanPropertiesDecreaseItem();
    	HumanPropertiesIncreaseItem increase_item = new HumanPropertiesIncreaseItem();
    	output_item.setCycleItem(cycle_item);
    	decrease_item.setCycleItem(cycle_item);
    	increase_item.setCycleItem(cycle_item);

    	GameRegistry.registerItem(cycle_item.setUnlocalizedName("cycle_item").setCreativeTab(CreativeTabs.tabMisc), "cycle_item");
    	GameRegistry.registerItem(output_item.setUnlocalizedName("output_item").setCreativeTab(CreativeTabs.tabMisc), "output_item");
    	GameRegistry.registerItem(decrease_item.setUnlocalizedName("decrease_item").setCreativeTab(CreativeTabs.tabMisc), "decrease_item");
    	GameRegistry.registerItem(increase_item.setUnlocalizedName("increase_item").setCreativeTab(CreativeTabs.tabMisc), "increase_item");
	}

}
