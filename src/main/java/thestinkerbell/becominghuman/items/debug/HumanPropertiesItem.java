package thestinkerbell.becominghuman.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.Properties;

public class HumanPropertiesItem extends Item {
	
	protected Properties basic_property_list;
	protected Properties all_property_list;
	protected HumanPropertiesCycleItem cycle_item;

	public HumanPropertiesItem() {
		super();
	}

	public void setCycleItem(HumanPropertiesCycleItem cycle_item) {
		this.cycle_item = cycle_item;
	}
	
	public Integer getCyclingIndex() {
		return (cycle_item == null) ? -1 : cycle_item.getIndex();
	}
	
	protected void setListOfProperties(EntityPlayer player) {
		this.basic_property_list = HumanExtendedEntityProperties.get(player).human.getListOfBasicHumanProperties();
		this.all_property_list = HumanExtendedEntityProperties.get(player).human.getListOfAllHumanProperties();
	}
	

}