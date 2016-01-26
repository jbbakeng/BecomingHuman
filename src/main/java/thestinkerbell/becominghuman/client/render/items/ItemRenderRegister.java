package thestinkerbell.becominghuman.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.items.ModItems;

public final class ItemRenderRegister {
	
	public static String modid = BecomingHuman.MODID;
	
    public static void registerItemRenderer() {
    	reg(ModItems.tutorialItem);
    }

	public static void reg(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
