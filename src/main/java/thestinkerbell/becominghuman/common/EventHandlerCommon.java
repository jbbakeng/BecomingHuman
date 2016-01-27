package thestinkerbell.becominghuman.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.PlayerData;

public class EventHandlerCommon {
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Start e) {
		System.out.println("PlayerUseItemEvent.Start, Player: "+e.entityPlayer.getDisplayNameString()+", Item: "+e.item.getDisplayName());
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing e) {
	    if (e.entity instanceof EntityPlayer && PlayerData.get((EntityPlayer) e.entity) == null) {
	        PlayerData.register((EntityPlayer) e.entity);
	        System.out.println("Registering Playerdata");
	    }
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
	    if (e.entity instanceof EntityPlayer) {
	    	System.out.println("Syncing mana");
	        PlayerData.get((EntityPlayer) e.entity).syncMana();
	    }
	}
	
	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone e) {
	    NBTTagCompound nbt = new NBTTagCompound();
	    PlayerData.get(e.original).saveReviveRelevantNBTData(nbt, e.wasDeath);
	    PlayerData.get(e.entityPlayer).loadNBTData(nbt);
	}
}
