package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.compound.PairedCompoundHumanProperty;

public class HumanPropertiesOutputItem extends HumanPropertiesItem {
	
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
			this.setListOfProperties(player);
			this.outputHumanProperties(player);

        }
        return stack;
    }

	private void outputHumanProperties(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("--- Human Properties ---"));
		for(Property property : all_property_list) {
			if(property instanceof PairedCompoundHumanProperty)
				player.addChatMessage(new ChatComponentText(property.getName()+": "+property.getValue()+" "+property.getUnit()));
			else
				player.addChatMessage(new ChatComponentText(property.getName()+": "+String.format("%.2f", property.getValue())+" "+property.getUnit()));
		}
	}
}
