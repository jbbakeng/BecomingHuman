package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertiesDecreaseItem extends HumanPropertiesCycleItem {
	
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
			this.getListOfProperties(player);
        	HumanProperty property = properties.get(this.getCyclingIndex());
        	HumanExtendedEntityProperties.get(player).properties.setValue(property.name, property.value-1);
        	player.addChatMessage(new ChatComponentText("- "+property.name+": "+property.value+" "+property.unit));

        }
        return stack;
    }
}
