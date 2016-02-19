package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public class MusclePainEffect extends PainEffect {

	public MusclePainEffect(int id) {
		super(	id,
				new ResourceLocation("becominghuman", "/textures/potioneffects/pain.png".substring(1)),
				true,
				0,
				"potion.musclepaineffect"
				);
		this.setIconIndex(2,0);
	}
}
