package thestinkerbell.becominghuman.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;

public class EventHandlerCommon {
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Start e) {
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing e) {
	    if (e.entity instanceof EntityPlayer && HumanExtendedEntityProperties.get((EntityPlayer) e.entity) == null) {
	    	HumanExtendedEntityProperties.register((EntityPlayer) e.entity);
	    }
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		//NTB data is loaded before this event
		//This is called on both server and client side, first server then client
	    if (e.entity instanceof EntityPlayer) {
	    	HumanExtendedEntityProperties.get((EntityPlayer) e.entity).syncAll();
	    }
	}
	
	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone e) {
	    NBTTagCompound nbt = new NBTTagCompound();
	    HumanExtendedEntityProperties.get(e.original).saveReviveRelevantNBTData(nbt, e.wasDeath);
	    HumanExtendedEntityProperties.get(e.entityPlayer).loadNBTData(nbt);
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent e) {
		if (e.entity instanceof EntityPlayer) {
			HumanExtendedEntityProperties.get((EntityPlayer) e.entity).applyPotionEffectsFromSymptoms();
		}
	}
}
