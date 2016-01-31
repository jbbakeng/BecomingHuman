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
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertiesOutputItem extends HumanPropertiesItem {
	
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
			this.getListOfProperties(player);
			player.addChatMessage(new ChatComponentText("--- Human Properties ---"));
        	for(HumanProperty property : properties) {
        		player.addChatMessage(new ChatComponentText(property.name+": "+property.value+" "+property.unit));
        	}

        }
        return stack;
    }
}
