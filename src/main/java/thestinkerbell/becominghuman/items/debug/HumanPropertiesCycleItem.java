package thestinkerbell.becominghuman.items.debug;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.HumanProperties;
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertiesCycleItem extends HumanPropertiesItem {

	private Integer index = 0;
	
	public HumanPropertiesCycleItem() {
		super();
	}
	
	public HumanPropertiesCycleItem(List<HumanProperty> properties) {
		this();
		this.properties = properties;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public void cycleIndex() {
		Integer index = (this.index + 1) % properties.size();
		this.index = index;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			this.setListOfProperties(player);
			this.cycleHumanPropertiesIndex(player);
		}
		return stack;
	}

	private void cycleHumanPropertiesIndex(EntityPlayer player) {
		this.cycleIndex();
		HumanProperty property = properties.get(this.index);
		player.addChatMessage(new ChatComponentText("o "+property.name+": "+property.value+" "+property.unit));
	}


}