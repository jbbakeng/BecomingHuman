package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertiesIncreaseItem extends HumanPropertiesItem {

	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
			this.setListOfProperties(player);
        	this.increaseHumanPropertyValue(player);

        }
        return stack;
    }

	private void increaseHumanPropertyValue(EntityPlayer player) {
		HumanProperty property = properties.get(this.getCyclingIndex());
		HumanExtendedEntityProperties.get(player).properties.setValue(property.name, property.value+1);
		player.addChatMessage(new ChatComponentText("+ "+property.name+": "+property.value+" "+property.unit));
	}
	
}
