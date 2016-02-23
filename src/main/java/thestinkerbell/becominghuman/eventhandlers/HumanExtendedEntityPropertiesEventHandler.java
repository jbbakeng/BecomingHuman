package thestinkerbell.becominghuman.eventhandlers;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.extendedentityproperties.HumanExtendedEntityProperties;
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
			//--- Symptoms
			HumanExtendedEntityProperties extended_properties = HumanExtendedEntityProperties.get((EntityPlayer) e.entity);
			//extended_properties.applyPotionEffectsFromSymptoms();
			
			//--- Influences
			//AirTemperature
			BiomeGenBase.TempCategory temp = e.entityLiving.worldObj.getBiomeGenForCoords(e.entity.getPosition()).getTempCategory();
			extended_properties.addInfluenceToQueue(new AirTemperatureInfluence(extended_properties.human, temp));
			
			//debugMovementSpeed(e);
			
			//extended_properties.applyInfluences();
		}
	}

	private void debugMovementSpeed(LivingUpdateEvent e) {
		//ai movementspeed
		double ai_movement_speed = e.entityLiving.getAIMoveSpeed();
		
		double bps = ai_movement_speed * 43.17;
		System.out.println("bps: "+bps);
		
		//constants	
		IAttributeInstance attribute = e.entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);
		//System.out.println("BaseValue: "+attribute.getBaseValue()+" AttributeValue: "+attribute.getAttributeValue());

		//actual movement
		double x = e.entityLiving.motionX;
		double y = e.entityLiving.motionY;
		double z = e.entityLiving.motionZ;
		boolean sprinting = e.entityLiving.isSprinting();
		//System.out.println("x: "+x+" y: "+y+" z: "+z);
		float f = MathHelper.sqrt_double(x * x + z * z);
		//float speed_km = f *0.277778F * 0.05F;
		//float speed_ms = (float) (f * bps);
		//System.out.println("f: "+f+" speed_ms: "+speed_ms+" speed_km: "+speed_km);
		System.out.println("f: "+f+" f*20: "+f*20);
		
		
		//strafing always 0, but moveForward shows intent to move it seems
		double moveForward = e.entityLiving.moveForward;
		double moveStrafing = e.entityLiving.moveStrafing;
		//System.out.println("moveForward: "+moveForward+"moveStrafingy: "+moveStrafing);
		
		
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
