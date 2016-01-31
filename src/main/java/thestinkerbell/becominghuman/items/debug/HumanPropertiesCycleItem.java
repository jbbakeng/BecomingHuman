package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertiesCycleItem extends HumanPropertiesItem {

	private Integer index = 0;
	
	public HumanPropertiesCycleItem() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			this.getListOfProperties(player);
			this.cycleIndex();
			HumanProperty property = properties.get(this.index);
			player.addChatMessage(new ChatComponentText("o "+property.name+": "+property.value+" "+property.unit));
		}
		return stack;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	private void cycleIndex() {
		Integer index = (this.index + 1) % properties.size();
		this.index = index;
	}


}