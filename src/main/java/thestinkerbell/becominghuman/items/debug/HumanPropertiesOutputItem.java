package thestinkerbell.becominghuman.items.debug;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
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
		for(HumanProperty property : all_property_list) {
			if(property instanceof PairedCompoundHumanProperty)
				player.addChatMessage(new ChatComponentText(property.name+": "+property.getValue()+" "+property.unit));
			else
				player.addChatMessage(new ChatComponentText(property.name+": "+String.format("%.2f", property.getValue())+" "+property.unit));
		}
	}
}
