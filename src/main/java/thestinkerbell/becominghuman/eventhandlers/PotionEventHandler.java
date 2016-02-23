package thestinkerbell.becominghuman.eventhandlers;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.BecomingHuman;

public class PotionEventHandler {
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent e) {
		/*
		if(e.entityLiving.isPotionActive(BecomingHuman.potions.pain)) {
			if(e.entityLiving.getActivePotionEffect(BecomingHuman.potions.pain).getDuration() == 0) {
				e.entityLiving.removePotionEffect(BecomingHuman.potions.pain.id);
				return; 
			}
		} else if(e.entityLiving.worldObj.rand.nextInt(30) == 0) {
			e.entityLiving.attackEntityFrom(DamageSource.generic, 2); //2 == 1 heart
		}
		*/
	}
}
