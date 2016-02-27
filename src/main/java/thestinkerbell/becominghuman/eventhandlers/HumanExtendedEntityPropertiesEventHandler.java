package thestinkerbell.becominghuman.eventhandlers;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.extendedentityproperties.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.influences.natural.AirTemperatureInfluence;
import thestinkerbell.becominghuman.human.influences.natural.BreathingGermsInfluence;
import thestinkerbell.becominghuman.human.influences.natural.DrinkingWaterInfluence;
import thestinkerbell.becominghuman.human.influences.natural.EatingInfluence;
import thestinkerbell.becominghuman.human.influences.natural.HungerInfluence;
import thestinkerbell.becominghuman.human.influences.natural.MovementInfluence;
import thestinkerbell.becominghuman.human.influences.natural.TimeInfluence;
import thestinkerbell.becominghuman.human.influences.natural.TouchingGermsInfluence;
import thestinkerbell.becominghuman.utilities.SpeedConverter;
import thestinkerbell.becominghuman.utilities.Utilities;

public class HumanExtendedEntityPropertiesEventHandler {
	
	private static Vec3 server_last_tick_player_position = new Vec3(0,0,0);
	
	private Vec3 getServersMotionVector(LivingUpdateEvent e) {
		Vec3 diff = server_last_tick_player_position.subtract(e.entityLiving.getPositionVector());
		server_last_tick_player_position = e.entityLiving.getPositionVector();
		return diff;
	}
	
	// --- Events
	
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onEntityConstructing(EntityConstructing e) {
	    if (e.entity instanceof EntityPlayer && HumanExtendedEntityProperties.get((EntityPlayer) e.entity) == null) {
	    	HumanExtendedEntityProperties.register((EntityPlayer) e.entity);
	    }
	}
	
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		//NTB data is loaded before this event
		//This is called on both server and client side, first server then client
	    if (e.entity instanceof EntityPlayer) {
	    	HumanExtendedEntityProperties.get((EntityPlayer) e.entity).syncAll();
	    }
	}
	
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onPlayerCloned(PlayerEvent.Clone e) {
	    NBTTagCompound nbt = new NBTTagCompound();
	    HumanExtendedEntityProperties.get(e.original).saveReviveRelevantNBTData(nbt, e.wasDeath);
	    HumanExtendedEntityProperties.get(e.entityPlayer).loadNBTData(nbt);
	}
	
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onLivingUpdate(LivingUpdateEvent e) {
		
		if (e.entity instanceof EntityPlayer) {
			HumanExtendedEntityProperties extended_properties = HumanExtendedEntityProperties.get((EntityPlayer) e.entity);
			if(extended_properties.isServerSide()) {
				
				//--- Symptoms
				extended_properties.applyPotionEffectsFromSymptoms();
				
				//--- Influences
				//		AirTemperature
				BiomeGenBase.TempCategory temp = e.entityLiving.worldObj.getBiomeGenForCoords(e.entity.getPosition()).getTempCategory();
				extended_properties.addInfluenceToQueue(new AirTemperatureInfluence(extended_properties.human, temp));
				
				//		Movement
				Vec3 server_motion = getServersMotionVector(e);
				double speed_kph = SpeedConverter.getSpeed_kph(server_motion.xCoord, server_motion.zCoord);
				extended_properties.addInfluenceToQueue(new MovementInfluence(extended_properties.human, speed_kph));
				
				//		Hunger
				FoodStats food_stats = ((EntityPlayer)e.entityLiving).getFoodStats();
				extended_properties.addInfluenceToQueue(new HungerInfluence(extended_properties.human, food_stats));
				
				//		Breathing
				double chance_of_happening_once_a_week = Utilities.getChanceOfHappening(Utilities.weeks_to_ticks(1));
				if(Utilities.chanceOccured(chance_of_happening_once_a_week)) {					
					extended_properties.addInfluenceToQueue(new BreathingGermsInfluence(extended_properties.human));
				}
				
				//		Time
				extended_properties.addInfluenceToQueue(new TimeInfluence(extended_properties.human));
				
				//		APPLY INFLUENCES
				extended_properties.applyInfluences();
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Finish e) {
		if (e.entity instanceof EntityPlayer) {
			HumanExtendedEntityProperties extended_properties = HumanExtendedEntityProperties.get((EntityPlayer) e.entity);
			if(extended_properties.isServerSide()) {
				
				//--- Influences
				//		DrinkingWater
				if(e.item.getDisplayName().equals("Water Bottle")) {
					double water_liter = 0.5;
					extended_properties.addInfluenceToQueue(new DrinkingWaterInfluence(extended_properties.human, water_liter));
				}
				//		Eating
				if(e.item.getItem() instanceof ItemFood) {
					extended_properties.addInfluenceToQueue(new EatingInfluence(extended_properties.human, (ItemFood) e.item.getItem()));
				}
			
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.entity instanceof EntityPlayer) {
			HumanExtendedEntityProperties extended_properties = HumanExtendedEntityProperties.get((EntityPlayer) e.entity);			

			//--- Influences
			//		Touching
			if( (e.action == Action.LEFT_CLICK_BLOCK) || (e.action == Action.RIGHT_CLICK_BLOCK)) {
				double one_percent_chance_of_happening = 0.01;
				if(Utilities.chanceOccured(one_percent_chance_of_happening)) {
					extended_properties.addInfluenceToQueue(new TouchingGermsInfluence(extended_properties.human));					
				}
			}
		}
		
	}
}
