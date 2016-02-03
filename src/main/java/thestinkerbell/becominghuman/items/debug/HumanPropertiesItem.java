package thestinkerbell.becominghuman.items.debug;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;

public class HumanPropertiesItem extends Item {
	
	protected List<BasicHumanProperty> basic_property_list;
	protected List<HumanProperty> all_property_list;
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