package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public class SoarThroatEffect extends PainEffect {

	public SoarThroatEffect(int id) {
		super(	id,
				new ResourceLocation("becominghuman", "/textures/potioneffects/pain.png".substring(1)),
				true,
				0,
				"potion.soarthroateffect"
				);
		this.setIconIndex(3,0);
	}
}
