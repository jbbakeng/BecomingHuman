package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.properties.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.HumanProperty;

public class HumanPropertiesDecreaseItem extends HumanPropertiesItem {
	
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
			this.setListOfProperties(player);
        	this.decreaseHumanPropertyValue(player);

        }
        return stack;
    }

	private void decreaseHumanPropertyValue(EntityPlayer player) {
		HumanProperty property = properties.get(this.getCyclingIndex());
		HumanExtendedEntityProperties.get(player).properties.setValue(property.name, property.value-1);
		player.addChatMessage(new ChatComponentText("- "+property.name+": "+property.value+" "+property.unit));
	}
}
