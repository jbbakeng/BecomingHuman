package thestinkerbell.becominghuman.items.debug;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import thestinkerbell.becominghuman.human.properties.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.HumanProperty;

public class HumanPropertiesItem extends Item {
	
	protected List<HumanProperty> properties;
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
		this.properties = HumanExtendedEntityProperties.get(player).properties.getListOfHumanProperties();
	}
	

}