package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.GermHumanProperty;

public class HumanPropertiesCycleItem extends HumanPropertiesItem {

	private Integer index = 0;
	
	public HumanPropertiesCycleItem() {
		super();
	}
	
	public HumanPropertiesCycleItem(Properties properties) {
		this();
		this.basic_and_germ_property_list = properties;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public void cycleIndex() {
		Integer index = (this.index + 1) % basic_and_germ_property_list.size();
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
		Property property = basic_and_germ_property_list.get(this.index);
		
		if(property instanceof BasicHumanProperty){			
			player.addChatMessage(new ChatComponentText("o "+property.getName()+": "+property.getValue()+" "+property.getUnit()));
		} else if(property instanceof GermHumanProperty) {
			GermHumanProperty germ = (GermHumanProperty) property;
			player.addChatMessage(new ChatComponentText("o "+germ.getName()+": "+germ.getValue()+" "+germ.getUnit()+" antibodies: "+germ.getAntibodies()));
		}

	}


}