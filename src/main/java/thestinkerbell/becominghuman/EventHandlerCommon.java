package thestinkerbell.becominghuman;

import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerCommon {
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Start e) {
		System.out.println("PlayerUseItemEvent.Start, Player: "+e.entityPlayer.getDisplayNameString()+", Item: "+e.item.getDisplayName());
	}
}
