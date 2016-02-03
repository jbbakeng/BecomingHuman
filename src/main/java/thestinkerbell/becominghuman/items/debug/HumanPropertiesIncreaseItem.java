package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;

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
		BasicHumanProperty property = basic_property_list.get(this.getCyclingIndex());
		HumanExtendedEntityProperties.get(player).human.setValue(property.name, property.getValue()+1);
		player.addChatMessage(new ChatComponentText("+ "+property.name+": "+property.getValue()+" "+property.unit));
	}
	
}
