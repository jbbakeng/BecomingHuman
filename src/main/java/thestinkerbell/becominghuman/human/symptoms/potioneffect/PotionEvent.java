package thestinkerbell.becominghuman.human.symptoms.potioneffect;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thestinkerbell.becominghuman.BecomingHuman;

public class PotionEvent {
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent e) {
		if(e.entityLiving.isPotionActive(BecomingHuman.pain)) {
			if(e.entityLiving.getActivePotionEffect(BecomingHuman.pain).getDuration() == 0) {
				e.entityLiving.removePotionEffect(BecomingHuman.pain.id);
				return; 
			}
		} else if(e.entityLiving.worldObj.rand.nextInt(30) == 0) {
			e.entityLiving.attackEntityFrom(DamageSource.generic, 2); //2 == 1 heart
		}
	}
}
