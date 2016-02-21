package thestinkerbell.becominghuman.eventhandlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.extendedentityproperties.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.AirTemperatureInfluence;

public class HumanExtendedEntityPropertiesEventHandler {
	
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
			HumanExtendedEntityProperties extended_properties = HumanExtendedEntityProperties.get((EntityPlayer) e.entity);
			extended_properties.applyPotionEffectsFromSymptoms();
			
			BiomeGenBase.TempCategory temp = e.entityLiving.worldObj.getBiomeGenForCoords(e.entity.getPosition()).getTempCategory();
			extended_properties.addInfluenceToQueue(new AirTemperatureInfluence(extended_properties.human, temp));
			
			extended_properties.applyInfluences();
		}
	}
	
	@SubscribeEvent
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Start e) {
		//eating
		//drinking
		//etc.
	}
}
