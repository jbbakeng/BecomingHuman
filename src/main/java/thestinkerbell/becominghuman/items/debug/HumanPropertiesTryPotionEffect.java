package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.utilities.Utilities;

public class HumanPropertiesTryPotionEffect extends HumanPropertiesItem {

	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
			this.setListOfProperties(player);
        	this.applyPotionEffect(player);

        }
        return stack;
    }

	private void applyPotionEffect(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.blindness.id, Utilities.seconds_to_ticks(2)));
	}
	
	
}
