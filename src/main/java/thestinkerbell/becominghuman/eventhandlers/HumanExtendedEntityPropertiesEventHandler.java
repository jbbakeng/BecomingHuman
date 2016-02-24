package thestinkerbell.becominghuman.eventhandlers;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.extendedentityproperties.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.influences.AirTemperatureInfluence;
import thestinkerbell.becominghuman.human.influences.HungerInfluence;
import thestinkerbell.becominghuman.human.influences.MovementInfluence;
import thestinkerbell.becominghuman.utilities.SpeedConverter;

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
				
				extended_properties.applyInfluences();
			}
		}
	}

	private void debugMovementSpeed(LivingUpdateEvent e) {
		
		Vec3 diff = getServersMotionVector(e);
		
		double ground = MathHelper.sqrt_double(diff.xCoord * diff.xCoord + diff.zCoord * diff.zCoord);
		//System.out.println(""+diff.xCoord+" "+diff.zCoord);
		//System.out.println("serverside ms: "+SpeedConverter.getSpeed_ms(diff.xCoord, diff.zCoord));
		//sSystem.out.println("serverside kph: "+SpeedConverter.getSpeed_kph(diff.xCoord, diff.zCoord));
		
		double server_x = e.entityLiving.lastTickPosX - e.entityLiving.posX;
		double server_z = e.entityLiving.lastTickPosZ - e.entityLiving.posZ;
		//System.out.println("serverside: "+SpeedConverter.getSpeed_kph(server_x, server_z));
		
		//ai movementspeed
		double ai_movement_speed = e.entityLiving.getAIMoveSpeed();
		
		double bps = ai_movement_speed * 43.17;
		//System.out.println("bps: "+bps);
		
		//constants	
		IAttributeInstance attribute = e.entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);
		//System.out.println("BaseValue: "+attribute.getBaseValue()+" AttributeValue: "+attribute.getAttributeValue());

		//actual movement - client side... :(
		double ticks_per_second = 20;
		double x = e.entityLiving.motionX;
		double y = e.entityLiving.motionY;
		double z = e.entityLiving.motionZ;
		boolean sprinting = e.entityLiving.isSprinting();
		double f = MathHelper.sqrt_double(x * x + z * z);

		double speed_ms = (f * 36.48);

		//System.out.println("\nx: "+x+" y: "+y+" z: "+z);
		//System.out.println("\nf: "+f+" speed_ms: "+speed_ms);
		//float speed_km = f *0.277778F * 0.05F;
		//System.out.println("f: "+f+" speed_ms: "+speed_ms+" speed_km: "+speed_km);
		//System.out.println("f: "+f+" f*bps: "+f*bps);
		
		double moveForward = e.entityLiving.moveForward; //acc forward/backward
		double moveStrafing = e.entityLiving.moveStrafing; //acc left/right
		
		double speed_m_s = 0.0;//4.3D / moveForward; 
		//System.out.println("moveForward: "+moveForward+" speed_m_s: "+speed_m_s+"moveStrafingy: "+moveStrafing);
		
		
		//walking speed seems like a constant
		EntityPlayer player =  ((EntityPlayer)e.entityLiving);
		PlayerCapabilities capabilities = player.capabilities;
		double walking_speed = capabilities.getWalkSpeed();
		//System.out.println("walking_speed: "+walking_speed);
		
		
		//food stats
		//TODO create DrinkStats???
		FoodStats food_stats = player.getFoodStats();
	}
	
	@SubscribeEvent
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Start e) {
		//eating
		//drinking
		//etc.
	}
}
